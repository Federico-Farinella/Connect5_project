package com.example.connect5_project.dao;

import com.example.connect5_project.exceptions.DbConnectException;
import com.example.connect5_project.models.CentroSportivo;
import com.example.connect5_project.models.FieldDailyAvailability;
import com.example.connect5_project.utility.DbConfigurationManager;
import com.example.connect5_project.utility.JdbcConnect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;

public class DailiAvailabilityDao {

    public FieldDailyAvailability dbSearchAvailability (CentroSportivo center, LocalDate date) throws DbConnectException {
        DbConfigurationManager.DbConfigurationManagerInitialize();
        String dbUser = DbConfigurationManager.getDbUser();
        String password = DbConfigurationManager.getDbPass();
        JdbcConnect jdbcConnect;
        FieldDailyAvailability dailyAvailability = new FieldDailyAvailability();

        try {
            jdbcConnect = JdbcConnect.getUserConnection(dbUser, password);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Sto lanciando la prima ClassNotFoundException");
            throw new DbConnectException("Driver to connect database not found");
        } catch (SQLException e) {
            System.out.println("Sto lanciando la prima SQLException");
            throw new DbConnectException("Error with database connection");
        }


        try (Statement stmt = jdbcConnect.getConnection().createStatement()) {
            String sqlQuery = "SELECT * FROM daily_availability WHERE Sport_Center= '" + center.getName() + "' AND Date= '" + date + "';";
            ResultSet resultSet = stmt.executeQuery(sqlQuery);
            if (!resultSet.first()) {
                //throw new DbConnectException("Error with database connection");
                dailyAvailability.setResponse("Not match");
            } else {
                dailyAvailability.setResponse("Match");
                dailyAvailability.setDailyAvailability(resultSet);
            }
            dailyAvailability.setDailyAvailability(resultSet);
        } catch (SQLException e) {
            throw new DbConnectException("Error with database connection");  // Cambiato 5-05-2023
        }
        return dailyAvailability;
    }
}
