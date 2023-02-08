package com.example.connect_5.models.booking_Decorator;

public abstract class AbstractDecorator extends Booking {
    protected Booking booking;

    @Override
    public abstract String getDescription();

}
