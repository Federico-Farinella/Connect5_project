package com.example.connect5_project.take_booking.model.bookings_type_decorator;

import java.io.Serializable;

public class BasicBooking implements Type, Serializable {
    private final float price;
    private final String description;

    public BasicBooking(float price) {
        this.price = price;
        this.description = "Basic booking";
    }

    @Override
    public String getDescription () {
        return description;
    }


    @Override
    public float getPrice() {
        return price;
    }
}