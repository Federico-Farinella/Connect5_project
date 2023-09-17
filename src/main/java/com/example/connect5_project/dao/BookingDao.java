package com.example.connect5_project.dao;

import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.models.Booking;

public interface BookingDao {
    public boolean saveBooking(Booking booking) throws MyException;
}
