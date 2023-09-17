package com.example.connect5_project.take_booking.model;

import com.example.connect5_project.exceptions.MyException;

public interface BookingDao {
    public boolean saveBooking(Booking booking) throws MyException;
}
