package com.example.connect5_project.dao;

import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.TakeBookingException;
import com.example.connect5_project.models.Booking;
import com.example.connect5_project.utility.JdbcConnect;
import com.example.connect5_project.utility.TimeUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

public class BookingDao {
    String configFilePath = "src/main/resources/config.properties";

    /*public String saveBooking(Booking booking) {
        String sportCenterName = booking.getSportCenter().getName();
        String user = booking.getUser().getEmail();
        Date date = Date.valueOf(booking.getDate());
        String hour = booking.getHour();
        String description = booking.getOptional().getDescription();
        float price = booking.getOptional().getPrice();

        System.out.println("BookingDao: sto provando a salvare...\ncenter name: " + sportCenterName + ", user: " + user + ", " +
                "date: " + date + ", hour: " + hour + ", description: " + description + ", price: " + price);

        String dbUser;
        String pass;
        JdbcConnect conn;

        String ret;

        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            Properties prop = new Properties();
            prop.load(propsInput);
            dbUser = prop.getProperty("dbUser");
            pass = prop.getProperty("pass");
            conn = JdbcConnect.getUserConnection(dbUser, pass);
        } catch (IOException e) {
            ret = "Config file not found";
            return ret;
        } catch (ClassNotFoundException e) {
            ret = "Driver to connect database not found";
            return ret;
        } catch (SQLException e) {
            ret = "Error with database connection";
            return ret;
        }

        try (Statement stmt = conn.getConnection().createStatement()) {
            String sql = "INSERT INTO bookings (`ID_booking`, `Sport_center`, `Football_player`, `Date`, `Hour`, `Description`, `price`)" +
                    " VALUES (NULL, '" + sportCenterName + "', '" + user + "', '" + date + "', '" + hour
             + "', '" + description + "', '" + price + "');";

            //String sql2 = "INSERT INTO bookings (`ID_booking`, `Sport_center`, `Football_player`, `Date`, `Hour`, `Description`, `price`)" +
              //      " VALUES (NULL, '" + sportCenterName + "', '" + user + "', '" + date + "', '" + hour
              //      + "', '" + description + "', '" + price + 1 + "');";

            //Devo creare una classe (Model) Booking Service che sarà istanziata dal bookingController e che avrà due attributi di tipo daoBooking e daoAvailability
            // Questa classe avrà un metodo che verrà chiamato dal controller e che chiamerà prima il metodo  della daoBookingper inserire un nuovo appuntamento nel db (o in file) ,
            // e poi se questo darà response vero chiamerà anche il metodo che settera quell orario in quella data come non disponibile in quel centro sportivo;
            // se questo non riuscirà per qualche motivo allora verrà cancellato l appuntamento preso;

            //String sql = "INSERT INTO bookings VALUES ('" + sportCenterName + "', '" + user + "', '" + date + "', '" + hour
             //       + "', '" + description + "', '" + price + "');";

            int row = stmt.executeUpdate(sql);
            if (row == 1)
                ret = "Booking registered";
            else
                ret = "Booking not registered";
        } catch (SQLException e) {
            ret = "Error updating table Booking";
        }
        return ret;
    }*/

    public boolean saveBooking1(Booking booking) throws TakeBookingException {
        String sportCenterName = booking.getSportCenter().getName();
        String user = booking.getUser().getEmail();
        Date date = Date.valueOf(booking.getDate());
        String hour = booking.getHour();
        String description = booking.getOptional().getDescription();
        float price = booking.getOptional().getPrice();

        System.out.println("BookingDao: sto provando a salvare...\ncenter name: " + sportCenterName + ", user: " + user + ", " +
                "date: " + date + ", hour: " + hour + ", description: " + description + ", price: " + price);

        String dbUser;
        String pass;
        JdbcConnect dbInstance;

        boolean ret = false;

        try {
            dbInstance = JdbcConnect.getInstance();
        } catch (ConnectionDBException e) {
            //ret = "Config file not found";

            return ret;
        }
        String sql1 = "INSERT INTO bookings (`ID_booking`, `Sport_center`, `Football_player`, `Date`, `Hour`, `Description`, `price`)" +
                " VALUES (NULL, ?, ?, ?, ?, ?, ?);";
        try (Statement stmt = dbInstance.getConnection().createStatement();
             PreparedStatement preparedStmt = dbInstance.getConnection().prepareStatement(sql1)) {
            preparedStmt.setString(1, sportCenterName);
            preparedStmt.setString(2, user);
            preparedStmt.setDate(3, date);
            preparedStmt.setString(4, hour);
            preparedStmt.setString(5, description);
            preparedStmt.setFloat(6, price);
            String sql = "INSERT INTO bookings (`ID_booking`, `Sport_center`, `Football_player`, `Date`, `Hour`, `Description`, `price`)" +
                    " VALUES (NULL, '" + sportCenterName + "', '" + user + "', '" + date + "', '" + hour
                    + "', '" + description + "', '" + price + "');";

            //int row = stmt.executeUpdate(sql);
            int row = preparedStmt.executeUpdate();
            preparedStmt.close();
            if (row == 1) {
                String columnHour = TimeUtils.hourConverter(hour);
                //ret = "Booking registered";
                String sql2 = "UPDATE daily_availability " +
                        "SET " + columnHour + " = 1 " +
                        "WHERE `Sport_center` = ? AND `Date` = ?;";
                try (PreparedStatement preparedStmt2 = dbInstance.getConnection().prepareStatement(sql2)) {
                    //System.out.println(TimeUtils.hourConverter(hour));
                    //preparedStmt2.setString(1, TimeUtils.hourConverter(hour));
                    System.out.println(sportCenterName);
                    preparedStmt2.setString(1, sportCenterName);
                    System.out.println(date);
                    preparedStmt2.setDate(2, date);
                    row = preparedStmt2.executeUpdate();
                    if (row == 1)
                        ret = true;
                    else if (row == 0)
                        System.out.println("Non riesco a aggiornare dailyAvailability");
                } catch (SQLException e) {
                    System.out.println("Dao dailyAvailability update error");
                }
                //ret = true;
            }
            else {
                System.out.println("Dao dailyAvailability update failed");
                throw new TakeBookingException("Booking request failed");
                //ret = "Booking not registered";
            }
        } catch (SQLException e) {
            throw new TakeBookingException("Booking request failed");
            //ret = "Error updating table Booking";
        }
        return ret;
    }
}
