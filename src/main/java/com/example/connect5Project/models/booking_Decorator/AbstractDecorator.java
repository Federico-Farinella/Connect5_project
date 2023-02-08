package com.example.connect5Project.models.booking_Decorator;

public abstract class AbstractDecorator extends Booking {
    protected Booking booking;

    @Override
    public abstract String getDescription();

}
