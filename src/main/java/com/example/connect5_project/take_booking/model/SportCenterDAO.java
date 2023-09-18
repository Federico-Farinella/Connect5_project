package com.example.connect5_project.take_booking.model;

import com.example.connect5_project.take_booking.bean.SearchResultBeanResponse;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.SportCenterException;
import com.example.connect5_project.utility.JdbcConnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SportCenterDAO {

    public SearchResultBeanResponse dbSearchCenters(String nameToSearch, String cityToSearch) throws SportCenterException {  //Cambiato return da ResultSet a SearchResultBean
        SearchResultBeanResponse responseBean = new SearchResultBeanResponse();
        JdbcConnect dbInstance;
        try {
            dbInstance = JdbcConnect.getInstance();
        } catch (ConnectionDBException e) {
            return responseBean;
        }

        try (Statement stmt = dbInstance.getConnection().createStatement()) {
            String sql = "SELECT * FROM sport_center WHERE Name = '" + nameToSearch + "' AND City = '" + cityToSearch + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                throw new SportCenterException("Not match");
            } else {
                List<SportCenter> searchResults = new ArrayList<>();
                SportCenter center;
                String centerName;
                String centerCity;
                String address;
                String owner;
                String image;
                float fieldPrice;
                do {
                    String name = "Name";
                    centerName = rs.getString(name);
                    String city = "City";
                    centerCity = rs.getString(city);
                    String addr = "Address";
                    address = rs.getString(addr);
                    String ownerEmail = "OwnerEmail";
                    owner = rs.getString(ownerEmail);
                    String img = "Image";
                    image = rs.getString(img);
                    String fldPrice = "BasePrice";
                    fieldPrice = rs.getFloat(fldPrice);
                    center = new SportCenter(centerName, centerCity, address, owner, image, fieldPrice);
                    searchResults.add(center);
                } while(rs.next());
                SportCentersSearchResults centersResults = new SportCentersSearchResults(searchResults);

               responseBean.setListOfCenters(centersResults);
            }
        } catch (SQLException e) {
            throw  new SportCenterException("Error creating statement");
        }
        return responseBean;
    }

    public SearchResultBeanResponse dbSearchCentersByName(String nameToSearch) throws SportCenterException { //Cambiato return anche qui
        String dbUser;
        String pass;
        JdbcConnect jdbc;

        SearchResultBeanResponse resultBean = new SearchResultBeanResponse();

        String configFilePath = "src/main/resources/config.properties";
        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            dbUser = prop.getProperty("dbUser");
            pass = prop.getProperty("pass");
            jdbc = JdbcConnect.getUserConnection(dbUser, pass);
        } catch (IOException e) {
            throw  new SportCenterException("Config file not found", e);
        } catch (ClassNotFoundException e) {
            throw new SportCenterException("Driver to connect database not found", e);
        } catch (SQLException e) {
            throw new SportCenterException("Error with database connection", e);
        }

        try (Statement stmt = jdbc.getConnection().createStatement()) {
            String sql = "SELECT * FROM sport_center WHERE Name = '" + nameToSearch + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                throw new SportCenterException("Not match");
            } else {
                List<SportCenter> searchResults = new ArrayList<>();
                SportCenter center;
                String centerName;
                String centerCity;
                String address;
                String owner;
                String image;
                float fieldPrice;
                do {
                    String name = "Name";
                    centerName = rs.getString(name);
                    String city = "City";
                    centerCity = rs.getString(city);
                    String addr = "Address";
                    address = rs.getString(addr);
                    String ownerEmail = "OwnerEmail";
                    owner = rs.getString(ownerEmail);
                    String img = "Image";
                    image = rs.getString(img);
                    String fldPrice = "BasePrice";
                    fieldPrice = rs.getFloat(fldPrice);
                    center = new SportCenter(centerName, centerCity, address, owner, image, fieldPrice);
                    searchResults.add(center);
                } while(rs.next());
                SportCentersSearchResults centersResults = new SportCentersSearchResults(searchResults);

                resultBean.setListOfCenters(centersResults);
            }
        } catch (SQLException e) {
            throw new SportCenterException("Error creating statement");
        }
        return resultBean;
    }

    public SearchResultBeanResponse dbSearchCentersByCity(String cityToSearch) throws SportCenterException {

        SearchResultBeanResponse responseBean = new SearchResultBeanResponse();
        JdbcConnect dbInstance;
        try {
            dbInstance = JdbcConnect.getInstance();
        } catch (ConnectionDBException e) {
            return responseBean;
        }

        List<SportCenter> searchResults = new ArrayList<>();

        try (Statement stmt = dbInstance.getConnection().createStatement()) {
            String sql = "SELECT * FROM sport_center WHERE City = '" + cityToSearch + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.first()) {
                SportCenter center;
                String centerName;
                String centerCity;
                String address;
                String owner;
                String image;
                float fieldPrice;
                do {
                    String name = "Name";
                    centerName = rs.getString(name);
                    String city = "City";
                    centerCity = rs.getString(city);
                    String addr = "Address";
                    address = rs.getString(addr);
                    String ownerEmail = "OwnerEmail";
                    owner = rs.getString(ownerEmail);
                    String img = "Image";
                    image = rs.getString(img);
                    String fldPrice = "BasePrice";
                    fieldPrice = rs.getFloat(fldPrice);
                    center = new SportCenter(centerName, centerCity, address, owner, image, fieldPrice);
                    searchResults.add(center);
                } while(rs.next());
            }
            SportCentersSearchResults centersResults = new SportCentersSearchResults(searchResults);

            responseBean.setListOfCenters(centersResults);
        } catch (SQLException e) {
            throw new SportCenterException("Error creating statement");
        }
        return  responseBean;
    }
}
