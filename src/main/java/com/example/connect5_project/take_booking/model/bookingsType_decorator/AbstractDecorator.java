package com.example.connect5_project.take_booking.model.bookingsType_decorator;

import java.io.Serializable;

public abstract class AbstractDecorator implements Type, Serializable {
    private final Type type;

    public AbstractDecorator(Type booking) {
        this.type = booking;
    }

    @Override
    public String getDescription() {
        return this.type.getDescription();
    }
    @Override
    public float getPrice() {
        return this.type.getPrice();
    }
}
