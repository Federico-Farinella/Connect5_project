package com.example.connect5_project.bean;

import com.example.connect5_project.models.CentroSportivo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchResultBeanOut {
    private Float fieldPrice;
    private List<CentroSportivo> listOfCenters;
    private String daoResponse;

    public String getDaoResponse() {
        return daoResponse;
    }

    public void setDaoResponse(String daoResponse) {
        if (daoResponse.equals("Config file not found") || daoResponse.equals("Driver to connect database not found")
            || daoResponse.equals("Error with database connection") || daoResponse.equals("Error creating statement")) {
            this.daoResponse = "Error data";
        }
        else
            this.daoResponse = daoResponse;
    }

    public List<CentroSportivo> getListOfCenters() {
        return listOfCenters;
    }

    public void setListOfCenters(ResultSet rs) throws SQLException {
        ArrayList<CentroSportivo> centers = new ArrayList<>();
        CentroSportivo cent;
        if (rs.first()) {
            String name;
            String city;
            String address;
            String owner;
            String image;
            do {
                name = rs.getString("Name");
                city = rs.getString("City");
                address = rs.getString("Address");
                owner = rs.getString("OwnerEmail");
                image = rs.getString("Image");
                fieldPrice = rs.getFloat("BasePrice");
                cent = new CentroSportivo(name, city, address, owner, image, fieldPrice);
                centers.add(cent);
            } while (rs.next());
        }
        listOfCenters = centers;
    }

    public List<CentroSportivo> modelling(ResultSet rs) throws SQLException {
        ArrayList<CentroSportivo> centers = new ArrayList<>();
        CentroSportivo cnt;

        if (rs.first()) {
            String name;
            String city;
            String address;
            String owner;
            String image;
            do {
                name = rs.getString("Name");
                city = rs.getString("City");
                address = rs.getString("Street");
                owner = rs.getString("OwnerEmail");
                image = rs.getString("Image");
                fieldPrice = rs.getFloat("BasePrice");
                cnt = new CentroSportivo(name, city, address, owner, image, fieldPrice);
                centers.add(cnt);
            } while (rs.next());
        }
        return centers;
    }



}
