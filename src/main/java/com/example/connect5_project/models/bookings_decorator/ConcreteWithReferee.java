package com.example.connect5_project.models.bookings_decorator;

public class ConcreteWithReferee extends AbstractDecorator {

    public ConcreteWithReferee(Type booking) {
        super(booking);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with referee";
    }

    @Override
    public float getPrice() {
        return super.getPrice() + 40f;
    }
}
