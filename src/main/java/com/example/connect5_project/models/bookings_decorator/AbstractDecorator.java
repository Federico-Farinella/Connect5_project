package com.example.connect5_project.models.bookings_decorator;

public abstract class AbstractDecorator implements Optional {
    private final Optional booking;

    public AbstractDecorator(Optional booking) {
        this.booking = booking;
    }

    @Override
    public String getDescription() {
        return booking.getDescription();
    }
    @Override
    public float getPrice() {
        return booking.getPrice();
    }
}
