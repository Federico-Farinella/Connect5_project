package com.example.connect5_project.bean;

import com.example.connect5_project.models.CentroSportivo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchResultBean {
    private static String name;
    private static String city;
    private static String address;
    private static String owner;
    //private static ImageView image;
    private static String image;
    private static Float fieldPrice;
    private ArrayList<CentroSportivo> listOfCenters;

    public ArrayList<CentroSportivo> getListOfCenters() {
        return listOfCenters;
    }

    public void setListOfCenters(ResultSet rs) throws SQLException {
        ArrayList<CentroSportivo> centers = new ArrayList<>();
        CentroSportivo cent;
        if (rs.first()) {
            do {
                name = rs.getString("Name");
                city = rs.getString("City");
                address = rs.getString("Address");
                owner = rs.getString("OwnerEmail");
                image = rs.getString("Image");
                //field_price = 0.0;
                fieldPrice = rs.getFloat("BasePrice");
                cent = new CentroSportivo(name, city, address, owner, image, fieldPrice);
                centers.add(cent);
            } while (rs.next());
        }
        listOfCenters = centers;
    }

    public ArrayList<CentroSportivo> modelling(ResultSet rs) throws SQLException {
        ArrayList<CentroSportivo> centers = new ArrayList<>();
        CentroSportivo cnt;
        if (rs.first()) {
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
