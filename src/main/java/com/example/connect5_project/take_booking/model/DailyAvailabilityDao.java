package com.example.connect5_project.take_booking.model;

import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.DailyAvailabilityException;
import com.example.connect5_project.utility.JdbcConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class DailyAvailabilityDao {

    public FieldDailyAvailability dbSearchAvailability (SportCenter center, LocalDate date) throws ConnectionDBException, DailyAvailabilityException {
        JdbcConnect jdbcConnect;
        FieldDailyAvailability dailyAvailability = new FieldDailyAvailability();

        try {
            jdbcConnect = JdbcConnect.getInstance();
        }
        catch (ConnectionDBException | SQLException e) {
            throw new ConnectionDBException("We have problems with system. Try later.");
        }


        try (Statement stmt = jdbcConnect.getConnection().createStatement()) {
            String sqlQuery = "SELECT * FROM daily_availability WHERE Sport_Center= '" + center.getName() + "' AND Date= '" + date + "';";
            ResultSet resultSet = stmt.executeQuery(sqlQuery);
            if (!resultSet.first()) {
                throw new DailyAvailabilityException("No daily availability");
            } else {
                dailyAvailability.setDailyAvailability(resultSet);
            }
            dailyAvailability.setDailyAvailability(resultSet);
        } catch (SQLException e) {
            throw new ConnectionDBException("Error with database connection");  // Cambiato 5-05-2023
        }
        return dailyAvailability;
    }
}
