package com.example.connect5_project.take_booking.model;

import com.example.connect5_project.exceptions.MyException;

import java.io.*;

public class BookingDaoFs implements BookingDao {
    static final String BOOKINGSPATH="src/main/resources/bookingsOnFS.dat";

    @Override
    public boolean saveBooking(Booking booking) throws MyException {
        //FileOutputStream fileOutput;
        //ObjectOutputStream outputStream;
        try (FileOutputStream fileOutput = new FileOutputStream(BOOKINGSPATH);
             ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);){
            //fileOutput = new FileOutputStream(BOOKINGSPATH);
            System.out.println("Ho istanziato fileUoutput");
            //outputStream = new ObjectOutputStream(fileOutput);
            System.out.println("Sto per scrivere");
            outputStream.writeObject(booking);
            System.out.println("Ho scritto");
            //outputStream.close();
        } catch (IOException e) {
            throw new MyException("Error saving booking");
        }

        /*try {
            //outputStream.close();
        } catch (IOException e) {
            throw new MyException("System error");
        }*/

        return true;

    }
}
