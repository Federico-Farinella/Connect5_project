package com.example.connect5_project.dao;

import com.example.connect5_project.models.Booking;
import com.example.connect5_project.utility.JdbcConnect;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Properties;

public class BookingDao {
    String configFilePath = "src/main/resources/config.properties";

    public String saveBooking(Booking booking) {
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

            /*String sql2 = "INSERT INTO bookings (`ID_booking`, `Sport_center`, `Football_player`, `Date`, `Hour`, `Description`, `price`)" +
                    " VALUES (NULL, '" + sportCenterName + "', '" + user + "', '" + date + "', '" + hour
                    + "', '" + description + "', '" + price + 1 + "');";*/

            //Devo creare una classe (Model) Booking Service che sarà istanziata dal bookingController e che avrà due attributi di tipo daoBooking e daoAvailability
            // Questa classe avrà un metodo che verrà chiamato dal controller e che chiamerà prima il metodo  della daoBookingper inserire un nuovo appuntamento nel db (o in file) ,
            // e poi se questo darà response vero chiamerà anche il metodo che settera quell orario in quella data come non disponibile in quel centro sportivo;
            // se questo non riuscirà per qualche motivo allora verrà cancellato l appuntamento preso;

            /*String sql = "INSERT INTO bookings VALUES ('" + sportCenterName + "', '" + user + "', '" + date + "', '" + hour
                    + "', '" + description + "', '" + price + "');";*/

            int row = stmt.executeUpdate(sql);
            if (row == 1)
                ret = "Booking registered";
            else
                ret = "Booking not registered";
        } catch (SQLException e) {
            ret = "Error updating table Booking";
        }
        return ret;
    }
}
