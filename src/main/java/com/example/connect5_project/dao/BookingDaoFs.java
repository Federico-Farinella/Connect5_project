package com.example.connect5_project.dao;

import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.models.Booking;

import java.io.*;

public class BookingDaoFs implements BookingDao {
    static final String BOOKINGSPATH="src/main/resources/bookingsOnFS.dat";

    @Override
    public boolean saveBooking(Booking booking) throws MyException {
        FileOutputStream fileOutput;
        ObjectOutputStream outputStream;
        try {
            fileOutput = new FileOutputStream(BOOKINGSPATH);
            System.out.println("Ho istanziato fileUoutput");
            outputStream = new ObjectOutputStream(fileOutput);
            System.out.println("Sto per scrivere");
            outputStream.writeObject(booking);
            System.out.println("Ho scritto");
            //outputStream.close();
        } catch (IOException e) {
            System.out.println("Sto lanciando la MyException1 da BookingDaoFs confirmBooking");
            throw new MyException("Error saving booking");
        }

        try {
            outputStream.close();
        } catch (IOException e) {
            System.out.println("Sto lanciando la MyException2 da BookingDaoFs confirmBooking");
            throw new MyException("System error");
        }

        return true;

    }
}
