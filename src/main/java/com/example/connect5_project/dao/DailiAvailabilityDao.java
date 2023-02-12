package com.example.connect5_project.dao;

import com.example.connect5_project.models.CentroSportivo;
import com.example.connect5_project.models.FieldDailyAvailability;
import com.example.connect5_project.utility.JdbcConnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;

public class DailiAvailabilityDao {
    String configFilePath = "src/main/resources/config.properties";



    public FieldDailyAvailability dbSearchAvailability (CentroSportivo center, LocalDate date) {
        // FieldDailyAvailability verrà creato dalla dao e restituito al controller che a sua volta returnerà alla ChooseBookingDataGUI
        String dbUser;
        String password;
        JdbcConnect jdbcConnect;
        FieldDailyAvailability dailyAvailability = new FieldDailyAvailability();

        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties properties = new Properties();
            properties.load(propsInput);
            dbUser = properties.getProperty("dbUser");
            password = properties.getProperty("pass");
            jdbcConnect = JdbcConnect.getUserConnection(dbUser, password);
        } catch (IOException e) {
            dailyAvailability.setResponse("Config file not found");
            return dailyAvailability;
        } catch (ClassNotFoundException e) {
            dailyAvailability.setResponse("Driver to connect database not found");
            return dailyAvailability;
        } catch (SQLException e) {
            dailyAvailability.setResponse("Error with database connection");
            return dailyAvailability;
        }

        try (Statement stmt = jdbcConnect.getConnection().createStatement();) {
            String sqlQuery = "SELECT * FROM daily_availability WHERE Sport_Center= '" + center.getName() + "' AND Date= '" + date + "';";
            ResultSet resultSet = stmt.executeQuery(sqlQuery);
            if (!resultSet.first()) {
                dailyAvailability.setResponse("Not match");
            } else {
                dailyAvailability.setResponse("Match");
                dailyAvailability.setDailyAvailability(resultSet);
            }
            dailyAvailability.setDailyAvailability(resultSet);
        } catch (SQLException e) {
            dailyAvailability.setResponse("Error reading data");
        }
        return dailyAvailability;
    }
}
