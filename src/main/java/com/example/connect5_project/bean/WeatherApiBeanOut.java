package com.example.connect5_project.bean;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


public class WeatherApiBeanOut {
    private int gap_day;
    private String city;
    //private LocalDate date_to_search;


    public int getGap_day() {
        return gap_day;
    }

    public void setGapDay(LocalDate date) {
        LocalDate now = LocalDate.now();
        gap_day = (int) DAYS.between(now, date);
        //this.gap_day = gap_day;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
