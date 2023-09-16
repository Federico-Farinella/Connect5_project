package com.example.connect5_project.bean;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


public class WeatherApiBeanOut {
    private int gapDay;
    private String city;


    public int getGapDay() {
        return gapDay;
    }

    public void setGapDay(LocalDate date) {
        LocalDate now = LocalDate.now();
        gapDay = (int) DAYS.between(now, date);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
