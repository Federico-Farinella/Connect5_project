package com.example.connect5_project.take_booking.model.bookings_type_decorator;

import java.io.Serializable;

public interface Type extends Serializable {
    public String getDescription();
    public float getPrice();
}
