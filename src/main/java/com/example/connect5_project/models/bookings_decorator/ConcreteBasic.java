package com.example.connect5_project.models.bookings_decorator;

import com.example.connect5_project.models.CentroSportivo;
import com.example.connect5_project.models.User;

import java.time.LocalDate;

public class ConcreteBasic implements Optional {
    private float price;

    public ConcreteBasic(float price) {
        this.price = price;
    }

    @Override
    public String getDescription () {
        return "Basic booking";
    }

    @Override
    public float getPrice() {
        return price;
    }
}