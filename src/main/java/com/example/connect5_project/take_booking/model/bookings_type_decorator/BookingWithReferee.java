package com.example.connect5_project.take_booking.model.bookings_type_decorator;

public class BookingWithReferee extends AbstractDecorator {

    public BookingWithReferee(Type booking) {
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
