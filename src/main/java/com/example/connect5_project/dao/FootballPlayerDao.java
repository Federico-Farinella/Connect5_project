package com.example.connect5_project.dao;

import com.example.connect5_project.bean.LoginBeanIn;
import com.example.connect5_project.bean.LoginBeanOut;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.login_exceptions.EmailNotRegisteredException;
import com.example.connect5_project.exceptions.login_exceptions.LoginException;
import com.example.connect5_project.models.User;
import com.example.connect5_project.utility.JdbcConnect;

import java.sql.*;

public class FootballPlayerDao {

    public User checkUser(LoginBeanIn beanIn) throws LoginException, ConnectionDBException {
        String email = beanIn.getEmail();
        String password = beanIn.getPassword();
        LoginBeanOut beanOut = new LoginBeanOut();
        JdbcConnect dbInstance;
        User user1 = null;
        try {
            dbInstance = JdbcConnect.getInstance();
        } catch (ConnectionDBException e) {
            throw new ConnectionDBException("DB Connection failed");
        }

        String querySql = "SELECT * FROM user WHERE email = ?;";
        try (PreparedStatement prepareStmt = dbInstance.getConnection().prepareStatement(querySql)) {
            prepareStmt.setString(1, email);
            ResultSet rs = prepareStmt.executeQuery();
            if (!rs.first()) {
                rs.close();
                throw new EmailNotRegisteredException("Email not registered");
            }

            else if (rs.first() && !rs.getString("password").equals(password)) {
                rs.close();
                return user1;
            }

            else {
                user1 = new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("nickName"));
            }
            rs.close();
        } catch (SQLException e) {
            throw new ConnectionDBException("Db execute failed");
        }
        return user1;
    }
}
