package com.example.connect5.DAO;

import com.example.connect5.bean.LoginBeanOut;
import com.example.connect5.models.LoggingUser;
import com.example.connect5.models.User;
import com.example.connect5.utility.JdbcConnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
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

            System.out.println("Stringa vuota: " + pass);
            //JdbcConnect conn = JdbcConnect.getUserConnection(dbUser, pass);
            System.out.println("Fin qui ok");
            //Connection connection = conn.getConnection();
            System.out.println("Fin qui anche");
            //Statement stmt = conn.getConnection().createStatement();
            String sql = "SELECT email FROM user WHERE email = '" + email + "';";
            ResultSet rs = stmt.executeQuery(sql);
            //stmt.close();
            System.out.println("Qui dopo prima query");
            if (!rs.first()) {
                beanOut.setResponse("Email not registered");
                //stmt.close();
                return beanOut;
            }

            String sql2 = "SELECT * FROM user WHERE email = '" + email + "' AND Password = '" + password + "';";
            //stmt2 = conn.getConnection().createStatement();
            rs = stmt2.executeQuery(sql2);
            //stmt.close();
            if (!rs.first()) {
                beanOut.setResponse("Password incorrect");
                return beanOut;
            } else {
                beanOut.setUser(new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getString("password"), rs.getString("nickName")));
                beanOut.setResponse("Match");
            }
        }/* catch (FileNotFoundException e) {
            //ret = "Config file not found";
            beanOut.setResponse("Config file not found");
        } catch (IOException e) {
            //ret = "Config file not loaded";
            beanOut.setResponse("Config file not loaded");
        }*/ catch (SQLException e) {
            //ret = "Error with database connection";
            beanOut.setResponse("Error with database connection");
        }/* catch (ClassNotFoundException e) {
            //ret = "Driver to connect database not found";
            beanOut.setResponse("Driver to connect database not found");
        }*/

        System.out.println(beanOut.getResponse());
        //ret = "Ok";
        return beanOut;
        //Continua da qui, vedi se va connessione e checka se l utente con questa email e pass è registrato nel database
    }
}
