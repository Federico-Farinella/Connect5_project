package com.example.connect5_project.models.bookings_decorator;

public class ConcreteBasic implements Type {
    private final float price;
    private final String description;

    public ConcreteBasic(float price) {
        this.price = price;
        this.description = "Basic booking";
    }

    @Override
    public String getDescription () {
        //return "Basic booking";
        return description;
    }

    @Override
    public float getPrice() {
        return price;
    }
}