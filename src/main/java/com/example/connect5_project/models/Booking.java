package com.example.connect5_project.models;

import com.example.connect5_project.models.bookings_decorator.ConcreteBasic;
import com.example.connect5_project.models.bookings_decorator.ConcreteWithReferee;
import com.example.connect5_project.models.bookings_decorator.ConcreteWithTunics;
import com.example.connect5_project.models.bookings_decorator.Type;

import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable {
    private CentroSportivo sportCenter;
    private User user;
    private final LocalDate date;
    private final String hour;
    private Type options;

    public Booking (CentroSportivo sportCenter, User user, LocalDate date, String hour) {    // price non andrà messo ma andrà messo il centro sportivo
        this.options = new ConcreteBasic(sportCenter.getFieldPrice());  // qui accederò a price dal centro sportivo
        this.sportCenter = sportCenter;
        this.user = user;
        this.date = date;
        this.hour = hour;
    }

    public void setWithReferee() {
        Type type = new ConcreteWithReferee(this.options);
        this.setOptional(type);
    }

    public void setWithTunics() {
        Type type = new ConcreteWithTunics(this.options);
        this.setOptional(type);
    }

    public CentroSportivo getSportCenter() {
        return sportCenter;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public void setOptional(Type type) {
        this.options = type;
    }

    public Type getOptional() {
        return options;
    }
}
