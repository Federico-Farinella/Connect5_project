package com.example.connect5_project;

import com.example.connect5_project.take_booking.model.Booking;
import com.example.connect5_project.take_booking.model.FootballPlayer;
import com.example.connect5_project.take_booking.model.SportCenter;
import com.example.connect5_project.take_booking.model.bookings_type_decorator.Type;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeTest {
    @Test
    public void setBookingWithRedereeTest() {
        SportCenter center = new SportCenter("Sport City", "Rome", "via Campus Biomedico", "sportcity@gmail.com",
                "", 40.0f);
        FootballPlayer player = new FootballPlayer("Federico", "Farinella", "fede@gmail.com", "fede");
        Booking booking = new Booking(center, player, LocalDate.now().plusDays(1), "20");
        booking.setWithReferee();
        assertEquals("Basic booking, with referee", booking.getOptional().getDescription());
    }
}
