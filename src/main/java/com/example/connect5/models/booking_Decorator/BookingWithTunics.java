package com.example.connect5.models.booking_Decorator;

public class BookingWithTunics extends AbstractDecorator {
    public BookingWithTunics(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String getDescription() {
        return booking.getDescription() + ", con casacche";
    }

    @Override
    public Float getPrice() {
        return booking.getPrice() + 10.5f;
    }
}
