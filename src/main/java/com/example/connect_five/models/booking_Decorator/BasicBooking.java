package com.example.connect_five.models.booking_Decorator;

import com.example.connect_five.models.CentroSportivo;
import com.example.connect_five.models.User;

public class BasicBooking extends Booking {

    public BasicBooking(Integer day, Integer month, Integer year, CentroSportivo centroSportivo, User user) {
        this.day = day;
        this.month = month;
        this. year = year;
        this. centro_sportivo = centroSportivo;
        this.user = user;
        this.price = centroSportivo.getFieldPrice();
        this.description = "Reservation";
    }

    public BasicBooking(Integer day, Integer month, Integer year, CentroSportivo centroSportivo, User user, String description) {
        this.day = day;
        this.month = month;
        this. year = year;
        this. centro_sportivo = centroSportivo;
        this.user = user;
        this.price = centroSportivo.getFieldPrice();
        this.description = description;
    }

    @Override
    public Float getPrice() {
        return price;
    }

}
