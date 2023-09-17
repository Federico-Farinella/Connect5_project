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
    String configFilePath = "src/main/resources/config.properties";

    public SearchResultBeanResponse dbSearchCenters(String name, String city) throws SportCenterException {  //Cambiato return da ResultSet a SearchResultBean
        SearchResultBeanResponse responseBean = new SearchResultBeanResponse();
        JdbcConnect dbInstance;
        try {
            dbInstance = JdbcConnect.getInstance();
        } catch (ConnectionDBException e) {
            return responseBean;
        }

        try (Statement stmt = dbInstance.getConnection().createStatement();) {
            String sql = "SELECT * FROM sport_center WHERE Name = '" + name + "' AND City = '" + city + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.first()) {
                SportCenterException exception = new SportCenterException("Not match");
                throw exception;
                //resultBean.setDaoResponse("Not match");
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
                    centerName = rs.getString("Name");
                    centerCity = rs.getString("City");
                    address = rs.getString("Address");
                    owner = rs.getString("OwnerEmail");
                    image = rs.getString("Image");
                    fieldPrice = rs.getFloat("BasePrice");
                    center = new SportCenter(centerName, centerCity, address, owner, image, fieldPrice);
                    searchResults.add(center);
                } while(rs.next());
                SportCentersSearchResults centersResults = new SportCentersSearchResults(searchResults);

               responseBean.setListOfCenters(centersResults);
                //resultBean.setDaoResponse("Match");
            }
        } catch (SQLException e) {
            SportCenterException exception = new SportCenterException("Error creating statement");
            throw exception;
            //resultBean.setDaoResponse("Error creating statement);
            //return resultBean;
        }
        //array = resultBean.setListOfCenters(rs);
        return responseBean;
    }

    public SearchResultBeanResponse dbSearchCentersByName(String name) throws SportCenterException { //Cambiato return anche qui
        String dbUser;
        String pass;
        JdbcConnect jdbc;

        ArrayList<SportCenter> array;
        SearchResultBeanResponse resultBean = new SearchResultBeanResponse();

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
                List<SportCenter> searchResults = new ArrayList<>();
                SportCenter center;
                String centerName;
                String centerCity;
                String address;
                String owner;
                String image;
                float fieldPrice;
                do {
                    centerName = rs.getString("Name");
                    centerCity = rs.getString("City");
                    address = rs.getString("Address");
                    owner = rs.getString("OwnerEmail");
                    image = rs.getString("Image");
                    fieldPrice = rs.getFloat("BasePrice");
                    center = new SportCenter(centerName, centerCity, address, owner, image, fieldPrice);
                    searchResults.add(center);
                } while(rs.next());
                SportCentersSearchResults centersResults = new SportCentersSearchResults(searchResults);

                resultBean.setListOfCenters(centersResults);
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

    public SearchResultBeanResponse dbSearchCentersByCity(String city) throws SportCenterException {

        SearchResultBeanResponse responseBean = new SearchResultBeanResponse();
        JdbcConnect dbInstance;
        try {
            dbInstance = JdbcConnect.getInstance();
        } catch (ConnectionDBException e) {
            return responseBean;
        }

        List<SportCenter> searchResults = new ArrayList<>();

        try (Statement stmt = dbInstance.getConnection().createStatement()) {
            String sql = "SELECT * FROM sport_center WHERE City = '" + city + "';";
            ResultSet rs = stmt.executeQuery(sql);
            //List<CentroSportivo> searchResults = new ArrayList<>();
            /*if (!rs.first()) {
                resultBean.setDaoResponse("Not Match");
                // Qui posso togliere questa eccezione e impostare direttamente come vuoto il resultBean.list_of_centers
                SportCenterException exception = new SportCenterException("Not match");
                throw exception;
                //resultBean.setDaoResponse("Not match");
            }*/
            if (rs.first()) {
                //responseBean.setDaoResponse("Match");
                //List<CentroSportivo> searchResults = new ArrayList<>();
                SportCenter center;
                String centerName;
                String centerCity;
                String address;
                String owner;
                String image;
                float fieldPrice;
                do {
                    centerName = rs.getString("Name");
                    centerCity = rs.getString("City");
                    address = rs.getString("Address");
                    owner = rs.getString("OwnerEmail");
                    image = rs.getString("Image");
                    fieldPrice = rs.getFloat("BasePrice");
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

// Modificare alcune cose:
/* 1- Il bean dell API From Controller Logico dovrei popolarlo direttamente dal suo costruttore con parametro Bean
 in entrata dall'utente!! (Meglio!!!)
 2- Gestire bene la lista di risultati di ricerca centri sportivi. La lista potrebbe (dovrebbe) essere un model
    e in questo modo potrei accedere più facilmente al centro sportivo che l'utente seleziona dalla lista grafica
*/
