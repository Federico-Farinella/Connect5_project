package com.example.connect5_project.dao;

import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.exceptions.TakeBookingException;
import com.example.connect5_project.models.Booking;
import com.example.connect5_project.utility.JdbcConnect;
import com.example.connect5_project.utility.TimeUtils;

import java.sql.*;

public class BookingDao {

    public boolean saveBooking1(Booking booking) throws TakeBookingException, ConnectionDBException {
        String sportCenterName = booking.getSportCenter().getName();
        String user = booking.getUser().getEmail();
        Date date = Date.valueOf(booking.getDate());
        String hour = booking.getHour();
        String description = booking.getOptional().getDescription();
        float price = booking.getOptional().getPrice();

        JdbcConnect dbInstance;

        boolean ret = false;

        try {
            dbInstance = JdbcConnect.getInstance();
        } catch (ConnectionDBException e) {
            return ret;
        }
        String sql1 = "INSERT INTO bookings (`ID_booking`, `Sport_center`, `Football_player`, `Date`, `Hour`, `Description`, `price`)" +
                " VALUES (NULL, ?, ?, ?, ?, ?, ?);";

        int row;
        try (PreparedStatement preparedStmt = dbInstance.getConnection().prepareStatement(sql1)) {
            preparedStmt.setString(1, sportCenterName);
            preparedStmt.setString(2, user);
            preparedStmt.setDate(3, date);
            preparedStmt.setString(4, hour);
            preparedStmt.setString(5, description);
            preparedStmt.setFloat(6, price);

            row = preparedStmt.executeUpdate();
        } catch (SQLException e) {
            throw new TakeBookingException("Booking request failed");
        }
        if (row == 1) {
            String columnHour = TimeUtils.hourConverter(hour);
            String sql2 = "UPDATE daily_availability " +
                    "SET " + columnHour + " = 1 " +
                    "WHERE `Sport_center` = ? AND `Date` = ?;";
            try (PreparedStatement preparedStmt2 = dbInstance.getConnection().prepareStatement(sql2)) {
                preparedStmt2.setString(1, sportCenterName);
                preparedStmt2.setDate(2, date);
                row = preparedStmt2.executeUpdate();
                if (row == 1)
                    ret = true;
            } catch (SQLException e) {
                // Qui potrei chiamare la delete booking
                throw new TakeBookingException("DailyAvailability update error");
            }
        } else {
            throw new TakeBookingException("Booking request failed");
        }
        return ret;
    }

    public void deleteBooking(Booking deletingBooking) throws MyException {
        String deletingCenterName = deletingBooking.getSportCenter().getName();
        String applicantUser = deletingBooking.getUser().getEmail();
        Date date = Date.valueOf(deletingBooking.getDate());
        String hour = deletingBooking.getHour();

        JdbcConnect dbInstance;

        try {
            dbInstance = JdbcConnect.getInstance();
        } catch (ConnectionDBException e) {
            throw new ConnectionDBException("We have problems with system. Try later.");
        }

        String sql1 = "DELETE FROM bookings WHERE `Sport_center` = ? AND `Football_player` = ? " +
                "AND `Date` = ? AND `Hour` = ? ;";

        int row;
        try (PreparedStatement preparedStmt = dbInstance.getConnection().prepareStatement(sql1)) {
            preparedStmt.setString(1, deletingCenterName);
            preparedStmt.setString(2, applicantUser);
            preparedStmt.setDate(3, date);
            preparedStmt.setString(4, hour);

            row = preparedStmt.executeUpdate();
            if (row == 0) {
                throw new TakeBookingException("Booking delete failed");
            }
        } catch (SQLException e) {
            throw new TakeBookingException("Booking delete failed");
        }
    }
}
