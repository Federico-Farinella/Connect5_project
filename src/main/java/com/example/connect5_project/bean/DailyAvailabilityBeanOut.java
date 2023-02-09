package com.example.connect5_project.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static java.time.temporal.ChronoUnit.DAYS;

public class DailyAvailabilityBeanOut {
    HashMap<String, ArrayList<String>> weather_by_hour;

    public HashMap<String, ArrayList<String>> getWeatherByHour() {
        return weather_by_hour;
    }

    public void setWeatherByHour(HashMap<String, ArrayList<String>> weather_by_hour) {
        this.weather_by_hour = weather_by_hour;
    }
    /*private LocalDate date_to_search;

    public void setDateToSearch(LocalDate date) {
        date_to_search = date;
    }*/
}
