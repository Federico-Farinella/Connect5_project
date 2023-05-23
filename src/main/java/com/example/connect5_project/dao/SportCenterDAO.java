package com.example.connect5_project.dao;

import com.example.connect5_project.bean.SearchResultBeanOut;
import com.example.connect5_project.exceptions.SportCenterException;
import com.example.connect5_project.models.CentroSportivo;
import com.example.connect5_project.utility.JdbcConnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class SportCenterDAO {
    String configFilePath = "src/main/resources/config.properties";

    public SearchResultBeanOut dbSearchCenters(String name, String city) throws SportCenterException {  //Cambiato return da ResultSet a SearchResultBean
        String dbUser;
        String pass;
        JdbcConnect jdbc;
        ArrayList<CentroSportivo> array;
        SearchResultBeanOut resultBean = new SearchResultBeanOut();
        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            dbUser = prop.getProperty("dbUser");
            pass = prop.getProperty("pass");
            jdbc = JdbcConnect.getUserConnection(dbUser, pass);
        } catch (IOException e) {
            SportCenterException exception = new SportCenterException("Config file not found", e);
            throw exception;
            //resultBean.setDaoResponse("Config file not found");
            //return resultBean;
        } catch (ClassNotFoundException e) {
            SportCenterException exception = new SportCenterException("Driver to connect database not found", e);
            throw exception;
            //resultBean.setDaoResponse("Driver to connect database not found");
            //return resultBean;
        } catch (SQLException e) {
            SportCenterException exception = new SportCenterException("Error with database connection", e);
            throw exception;
            //resultBean.setDaoResponse("Error with database connection");
            //return resultBean;
        }

        try (Statement stmt = jdbc.getConnection().createStatement();) {
            String sql = "SELECT * FROM sport_center WHERE Name = '" + name + "' AND City = '" + city + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                SportCenterException exception = new SportCenterException("Not match");
                throw exception;
                //resultBean.setDaoResponse("Not match");
            } else {
                //resultBean.setDaoResponse("Match");
                resultBean.setListOfCenters(rs); // Questo dovrei farlo qui nel Dao e impostare questa lista nel bean con un semplice setter
            }
        } catch (SQLException e) {
            SportCenterException exception = new SportCenterException("Error creating statement");
            throw exception;
            //resultBean.setDaoResponse("Error creating statement);
            //return resultBean;
        }
        //array = resultBean.setListOfCenters(rs);
        return resultBean;
    }

    public SearchResultBeanOut dbSearchCentersByName(String name) throws SportCenterException { //Cambiato return anche qui
        String dbUser;
        String pass;
        JdbcConnect jdbc;

        ArrayList<CentroSportivo> array;
        SearchResultBeanOut resultBean = new SearchResultBeanOut();

        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            dbUser = prop.getProperty("dbUser");
            pass = prop.getProperty("pass");
            jdbc = JdbcConnect.getUserConnection(dbUser, pass);
        } catch (IOException e) {
            SportCenterException exception = new SportCenterException("Config file not found", e);
            throw exception;
            //resultBean.setDaoResponse("Config file not found");
            //return resultBean;
        } catch (ClassNotFoundException e) {
            SportCenterException exception = new SportCenterException("Driver to connect database not found", e);
            throw exception;
            //resultBean.setDaoResponse("Driver to connect database not found");
            //return resultBean;
        } catch (SQLException e) {
            SportCenterException exception = new SportCenterException("Error with database connection", e);
            throw exception;
            //resultBean.setDaoResponse("Error with database connection");
            //return resultBean;
        }

        try (Statement stmt = jdbc.getConnection().createStatement();) {
            String sql = "SELECT * FROM sport_center WHERE Name = '" + name + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                SportCenterException exception = new SportCenterException("Not match");
                throw exception;
                //resultBean.setDaoResponse("Not match");
            } else {
                resultBean.setListOfCenters(rs);
                //resultBean.setDaoResponse("Match");
            }
        } catch (SQLException e) {
            SportCenterException exception = new SportCenterException("Error creating statement");
            throw exception;
            //resultBean.setDaoResponse("Error creating statement);
            //return resultBean;
        }
        return resultBean;
    }

    public SearchResultBeanOut dbSearchCentersByCity(String city) throws SportCenterException {
        String dbUser;
        String pass;
        JdbcConnect jdbc;

        ArrayList<CentroSportivo> array;
        SearchResultBeanOut resultBean = new SearchResultBeanOut();

        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            dbUser = prop.getProperty("dbUser");
            pass = prop.getProperty("pass");
            jdbc = JdbcConnect.getUserConnection(dbUser, pass);
        } catch (IOException e) {
            SportCenterException exception = new SportCenterException("Config file not found", e);
            throw exception;
            //resultBean.setDaoResponse("Config file not found");
            //return resultBean;
        } catch (ClassNotFoundException e) {
            SportCenterException exception = new SportCenterException("Driver to connect database not found", e);
            throw exception;
            //resultBean.setDaoResponse("Driver to connect database not found");
            //return resultBean;
        } catch (SQLException e) {
            SportCenterException exception = new SportCenterException("Error with database connection", e);
            throw exception;
            //resultBean.setDaoResponse("Error with database connection");
            //return resultBean;
        }

        try (Statement stmt = jdbc.getConnection().createStatement()) {
            String sql = "SELECT * FROM sport_center WHERE City = '" + city + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                SportCenterException exception = new SportCenterException("Not match");
                throw exception;
                //resultBean.setDaoResponse("Not match");
            } else {
                resultBean.setListOfCenters(rs);
                //resultBean.setDaoResponse("Match");
            }
        } catch (SQLException e) {
            SportCenterException exception = new SportCenterException("Error creating statement");
            throw exception;
            //resultBean.setDaoResponse("Error creating statement);
            //return resultBean;
        }
        return  resultBean;
    }
}
