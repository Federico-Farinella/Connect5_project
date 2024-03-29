package com.example.connect5_project.take_booking.model;

import com.example.connect5_project.exceptions.MyException;

import java.io.*;

public class BookingDaoFs implements BookingDao {
    static final String BOOKINGSPATH="src/main/resources/bookingsOnFS.dat";

    @Override
    public boolean saveBooking(Booking booking) throws MyException {
        try (FileOutputStream fileOutput = new FileOutputStream(BOOKINGSPATH);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);){
            outputStream.writeObject(booking);
        } catch (IOException e) {
            throw new MyException("Error saving booking");
        }

        return true;

    }
}
