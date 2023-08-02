package com.example.connect5_project.dao;

import com.example.connect5_project.bean.LoginBeanOut;
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
        //String ret;
        String email = user.getEmail();
        String password = user.getPassword();
        LoginBeanOut beanOut = new LoginBeanOut();
        String dbUser;
        String pass;
        JdbcConnect conn;
        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            dbUser = prop.getProperty("dbUser");
            pass = prop.getProperty("pass");
            conn = JdbcConnect.getUserConnection(dbUser, pass);
        } catch (IOException e) {
            beanOut.setResponse("Config file not found");
            return beanOut;
        } catch (ClassNotFoundException e) {
            beanOut.setResponse("Driver to connect database not found");
            return beanOut;
        } catch (SQLException e) {
            beanOut.setResponse("Error with database connection");
            return beanOut;
        }

        try (Statement stmt = conn.getConnection().createStatement();
                Statement stmt2 = conn.getConnection().createStatement()) {

            String sql = "SELECT email FROM user WHERE email = '" + email + "';";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Qui dopo prima query");
            if (!rs.first()) {
                beanOut.setResponse("Email not registered");
                return beanOut;
            }

            String sql2 = "SELECT * FROM user WHERE email = '" + email + "' AND Password = '" + password + "';";
            rs = stmt2.executeQuery(sql2);
            //stmt.close();
            if (!rs.first()) {
                beanOut.setResponse("Password incorrect");
                return beanOut;
            } else {
                beanOut.setUser(new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"), rs.getString("nickName")));
                beanOut.setResponse("Match");
            }
        } catch (SQLException e) {
            beanOut.setResponse("Error with database connection");
        }

        System.out.println(beanOut.getResponse());
        return beanOut;
    }
}
