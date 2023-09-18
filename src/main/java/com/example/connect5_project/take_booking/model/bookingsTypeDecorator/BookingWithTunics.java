package com.example.connect5_project.take_booking.model.bookingsTypeDecorator;

public class BookingWithTunics extends AbstractDecorator {
    public BookingWithTunics(Type booking) {
        super(booking);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with tunics";
    }

    @Override
    public float getPrice() {
        return super.getPrice() + 10f;
    }
}
