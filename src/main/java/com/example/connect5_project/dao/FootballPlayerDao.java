package com.example.connect5_project.dao;

import com.example.connect5_project.bean.LoginBeanOut;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.models.LoggingUser;
import com.example.connect5_project.models.User;
import com.example.connect5_project.utility.JdbcConnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class FootballPlayerDao {
    String configFilePath = "src/main/resources/config.properties";

    public FootballPlayerDao(){
    }

    public LoginBeanOut checkUser(LoggingUser user) {
        String email = user.getEmail();
        String password = user.getPassword();
        LoginBeanOut beanOut = new LoginBeanOut();
        JdbcConnect dbInstance;
        try {
            dbInstance = JdbcConnect.getInstance();
        } catch (ConnectionDBException e) {
            beanOut.setResponse("DB Connection failed");
            return beanOut;
        }

        String querySql = "SELECT * FROM user WHERE email = ?;";
        try (PreparedStatement prepareStmt = dbInstance.getConnection().prepareStatement(querySql);
                Statement stmt = dbInstance.getConnection().createStatement();
             Statement stmt2 = dbInstance.getConnection().createStatement()) {
            prepareStmt.setString(1, email);
            String sql = "SELECT email FROM user WHERE email = '" + email + "';";
            System.out.println("Qui prima della prima query");
            ResultSet rs = prepareStmt.executeQuery();
            System.out.println("Qui dopo prima query");
            if (!rs.first()) {
                beanOut.setResponse("Email not registered");
                System.out.println("Email not registered");
                return beanOut;
            }
            if (!(rs.getString("password").equals(password))) {
                beanOut.setResponse("Password incorrect");
                System.out.println("Password incorrect");
                return beanOut;
            }
            beanOut.setResponse("Match");
            beanOut.setUser(new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("nickName")));
            beanOut.setSuccess(true);
            rs.close();
        } catch (SQLException e) {
            //Dovrei lanciare una mia eccezione
            beanOut.setResponse("Error while creating the statement");
            System.out.println("Error while creating the statement");
        }
        return beanOut;
    }
}
