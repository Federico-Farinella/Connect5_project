package com.example.connect5_project.take_booking.model;

import com.example.connect5_project.take_booking.model.bookingsType_decorator.FootballPlayer;
import com.example.connect5_project.take_booking.model.bookingsType_decorator.ConcreteBasic;
import com.example.connect5_project.take_booking.model.bookingsType_decorator.ConcreteWithReferee;
import com.example.connect5_project.take_booking.model.bookingsType_decorator.ConcreteWithTunics;
import com.example.connect5_project.take_booking.model.bookingsType_decorator.Type;

import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable {
    private SportCenter sportCenter;
    private FootballPlayer footballPlayer;
    private final LocalDate date;
    private final String hour;
    private Type options;

    public Booking (SportCenter sportCenter, FootballPlayer footballPlayer, LocalDate date, String hour) {    // price non andrà messo ma andrà messo il centro sportivo
        this.options = new ConcreteBasic(sportCenter.getFieldPrice());  // qui accederò a price dal centro sportivo
        this.sportCenter = sportCenter;
        this.footballPlayer = footballPlayer;
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

    public SportCenter getSportCenter() {
        return sportCenter;
    }

    public FootballPlayer getUser() {
        return footballPlayer;
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
