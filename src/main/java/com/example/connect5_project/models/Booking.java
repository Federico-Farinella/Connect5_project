package com.example.connect5_project.models;

import com.example.connect5_project.models.bookings_decorator.ConcreteBasic;
import com.example.connect5_project.models.bookings_decorator.ConcreteWithReferee;
import com.example.connect5_project.models.bookings_decorator.ConcreteWithTunics;
import com.example.connect5_project.models.bookings_decorator.Optional;

import java.time.LocalDate;

public class Booking {
    private final LocalDate date;
    private final String hour;
    private Optional options;
    private final float price;

    public Booking (LocalDate date, String hour, float price) {    // price non andrà messo ma andrà messo il centro sportivo
        this.options = new ConcreteBasic(price);  // qui accederò a price dal centro sportivo

        this.date = date;
        this.hour = hour;
        this.price = price;
    }

    public void setWithReferee() {
        Optional optional = new ConcreteWithReferee(this.options);
        this.setOptional(optional);
    }

    public void setWithTunics() {
        Optional optional = new ConcreteWithTunics(this.options);
        this.setOptional(optional);
    }

    public void setOptional(Optional optional) {
        this.options = optional;
    }

    public Optional getOptions() {
        return options;
    }
}
