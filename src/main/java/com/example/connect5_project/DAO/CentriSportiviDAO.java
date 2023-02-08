package com.example.connect5_project.DAO;

import com.example.connect5_project.bean.SearchResultBean;
import com.example.connect5_project.models.CentroSportivo;
import com.example.connect5_project.utility.JdbcConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CentriSportiviDAO {
    //static Connection conn;
    JdbcConnect jdbc;
    Connection conn;
    public ResultSet dbSearchCenters(String name, String city) throws Exception{
        jdbc = JdbcConnect.getUserConnection();
        conn = jdbc.getConnection();
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM sport_center WHERE Name = '" + name + "' AND City = '" + city + "';";
        ResultSet rs = stmt.executeQuery(sql);
        SearchResultBean resultBean = new SearchResultBean();
        ArrayList<CentroSportivo> array = resultBean.modelling(rs);
        stmt.close();

        return rs;
    }

    public ResultSet dbSearchCentersByName(String name) throws Exception{
        jdbc = JdbcConnect.getUserConnection();
        conn = jdbc.getConnection();
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM sport_center WHERE Name = '" + name + "';";
        ResultSet rs = stmt.executeQuery(sql);
        stmt.close();
        return rs;
    }

    public ResultSet dbSearchCentersByCity(String city) throws Exception{
        jdbc = JdbcConnect.getUserConnection();
        conn = jdbc.getConnection();
        Statement stmt = conn.createStatement();

        String sql = "SELECT * FROM sport_center WHERE City = '" + city + "';";
        ResultSet rs = stmt.executeQuery(sql);
        stmt.close();
        return rs;
    }
}
