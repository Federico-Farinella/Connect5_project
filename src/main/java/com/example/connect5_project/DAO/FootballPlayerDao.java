package com.example.connect5_project.DAO;

import com.example.connect5_project.bean.LoginBeanOut;
import com.example.connect5_project.models.LoggingUser;
import com.example.connect5_project.models.User;
import com.example.connect5_project.utility.JdbcConnect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class FootballPlayerDao {
    String configFilePath = "src/main/resources/config.properties";
    //FileInputStream propsInput;
    public FootballPlayerDao(){
    }


    public LoginBeanOut checkUser(LoggingUser user) {
        //String ret;
        String email = user.getEmail();
        String password = user.getPassword();
        LoginBeanOut beanOut = new LoginBeanOut();

        FileInputStream propsInput;
        try {
            propsInput = new FileInputStream(configFilePath);
            Properties prop = new Properties();
            prop.load(propsInput);
            propsInput.close();
            String dbUser = prop.getProperty("dbUser");
            String pass = prop.getProperty("pass");
            System.out.println("Stringa vuota: " + pass);
            JdbcConnect conn = JdbcConnect.getUserConnection(dbUser, pass);
            System.out.println("Fin qui ok");
            //Connection connection = conn.getConnection();
            System.out.println("Fin qui anche");
            Statement stmt = conn.getConnection().createStatement();
            String sql = "SELECT email FROM user WHERE email = '" + email + "';";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Qui dopo prima query");
            if (!rs.first()) {
                beanOut.setResponse("Email not registered");
                stmt.close();
                return beanOut;
            }

            String sql2 = "SELECT * FROM user WHERE email = '" + email + "' AND Password = '" + password + "';";
            rs = stmt.executeQuery(sql2);
            stmt.close();
            if (!rs.first()) {
                beanOut.setResponse("Password incorrect");
                return beanOut;
            } else {
                beanOut.setUser(new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"), rs.getString("nickName")));
                beanOut.setResponse("Match");
            }
        } catch (FileNotFoundException e) {
            //ret = "Config file not found";
            beanOut.setResponse("Config file not found");
        } catch (IOException e) {
            //ret = "Config file not loaded";
            beanOut.setResponse("Config file not loaded");
        } catch (SQLException e) {
            //ret = "Error with database connection";
            beanOut.setResponse("Error with database connection");
        } catch (ClassNotFoundException e) {
            //ret = "Driver to connect database not found";
            beanOut.setResponse("Driver to connect database not found");
        }

        System.out.println(beanOut.getResponse());
        //ret = "Ok";
        return beanOut;
        //Continua da qui, vedi se va connessione e checka se l utente con questa email e pass è registrato nel database
    }
}
