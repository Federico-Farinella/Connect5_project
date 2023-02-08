package com.example.connect_5_project.models.booking_Decorator;

import com.example.connect_5_project.models.CentroSportivo;
import com.example.connect_5_project.models.User;

public abstract class Booking {
    protected Integer day;
    protected Integer year;
    protected Integer month;
    protected CentroSportivo centro_sportivo;
    protected User user;
    protected Float price;
    protected String description = "";

    public String getDescription() {
        return this.description;
    }

    public abstract Float getPrice();

}
