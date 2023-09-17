package com.example.connect5_project.models.bookings_decorator;

import java.io.Serializable;

public abstract class AbstractDecorator implements Type, Serializable {
    private final Type booking;

    public AbstractDecorator(Type booking) {
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
