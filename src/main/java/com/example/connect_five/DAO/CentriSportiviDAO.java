package com.example.connect_five.DAO;

import com.example.connect_five.bean.SearchResultBean;
import com.example.connect_five.models.CentroSportivo;
import com.example.connect_five.utility.JdbcConnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class CentriSportiviDAO {
    String configFilePath = "src/main/resources/config.properties";
    //static Connection conn;
    /*JdbcConnect jdbc;
    Connection conn;
    ResultSet rs;*/
    public SearchResultBean dbSearchCenters(String name, String city) {  //Cambiato return da ResultSet a SearchResultBean
        String dbUser;
        String pass;
        JdbcConnect jdbc;
        //Connection conn;
        ArrayList<CentroSportivo> array;
        SearchResultBean resultBean = new SearchResultBean();
        //jdbc = JdbcConnect.getUserConnection();
        //conn = jdbc.getConnection();
        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            dbUser = prop.getProperty("dbUser");
            pass = prop.getProperty("pass");
            jdbc = JdbcConnect.getUserConnection(dbUser, pass);
        } catch (IOException e) {
            resultBean.setDaoResponse("Config file not found");
            return resultBean;
        } catch (ClassNotFoundException e) {
            resultBean.setDaoResponse("Driver to connect database not found");
            return resultBean;
        } catch (SQLException e) {
            resultBean.setDaoResponse("Error with database connection");
            return resultBean;
        }

        try (Statement stmt = jdbc.getConnection().createStatement();) {
            String sql = "SELECT * FROM sport_center WHERE Name = '" + name + "' AND City = '" + city + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                resultBean.setDaoResponse("Not match");
                //stmt.close();
                //return resultBean;
            } else {
                resultBean.setDaoResponse("Match");
                resultBean.setListOfCenters(rs);
            }
            return resultBean;
            //resultBean = new SearchResultBean();
            //ArrayList<CentroSportivo> array = resultBean.modelling(rs);
            //resultBean.setListOfCenters(rs);
        } catch (SQLException e) {
            resultBean.setDaoResponse("Error opening database connession");
            return resultBean;
        }
        //array = resultBean.setListOfCenters(rs);
        //return resultBean;
    }

    public SearchResultBean dbSearchCentersByName(String name) throws Exception { //Cambiato return anche qui
        String dbUser;
        String pass;
        JdbcConnect jdbc;
        //Connection conn;
        ArrayList<CentroSportivo> array;
        SearchResultBean resultBean = new SearchResultBean();

        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            dbUser = prop.getProperty("dbUser");
            pass = prop.getProperty("pass");
            jdbc = JdbcConnect.getUserConnection(dbUser, pass);
        } catch (IOException e) {
            resultBean.setDaoResponse("Config file not found");
            return resultBean;
        } catch (ClassNotFoundException e) {
            resultBean.setDaoResponse("Driver to connect database not found");
            return resultBean;
        } catch (SQLException e) {
            resultBean.setDaoResponse("Error with database connection");
            return resultBean;
        }

        try (Statement stmt = jdbc.getConnection().createStatement();) {
            String sql = "SELECT * FROM sport_center WHERE Name = '" + name + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                resultBean.setDaoResponse("Not match");
                //stmt.close();
                //return resultBean;
            } else {
                resultBean.setListOfCenters(rs);
                resultBean.setDaoResponse("Match");
            }
            return resultBean;
        }
    }

    public SearchResultBean dbSearchCentersByCity(String city) throws Exception{
        String dbUser;
        String pass;
        JdbcConnect jdbc;
        //Connection conn;
        ArrayList<CentroSportivo> array;
        SearchResultBean resultBean = new SearchResultBean();

        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            dbUser = prop.getProperty("dbUser");
            pass = prop.getProperty("pass");
            jdbc = JdbcConnect.getUserConnection(dbUser, pass);
        } catch (IOException e) {
            resultBean.setDaoResponse("Config file not found");
            return resultBean;
        } catch (ClassNotFoundException e) {
            resultBean.setDaoResponse("Driver to connect database not found");
            return resultBean;
        } catch (SQLException e) {
            resultBean.setDaoResponse("Error with database connection");
            return resultBean;
        }

        try (Statement stmt = jdbc.getConnection().createStatement()) {
            String sql = "SELECT * FROM sport_center WHERE City = '" + city + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                resultBean.setDaoResponse("Not match");
            } else {
                resultBean.setListOfCenters(rs);
                resultBean.setDaoResponse("Match");
            }
            return resultBean;
        }
    }
}
