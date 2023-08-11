package com.example.connect5_project.models.bookings_decorator;

public class ConcreteWithTunics extends AbstractDecorator {
    public  ConcreteWithTunics(Type booking) {
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
