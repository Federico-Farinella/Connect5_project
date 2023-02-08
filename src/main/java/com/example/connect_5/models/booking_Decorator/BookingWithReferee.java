package com.example.connect_5.models.booking_Decorator;

public class BookingWithReferee extends AbstractDecorator {
    public BookingWithReferee(Booking booking) {
        this.booking = booking;
    }

    @Override
    public String getDescription() {
        return booking.getDescription() + ", con arbitro";
    }

    @Override
    public Float getPrice() {
        return booking.getPrice() + 30f;
    }
}
