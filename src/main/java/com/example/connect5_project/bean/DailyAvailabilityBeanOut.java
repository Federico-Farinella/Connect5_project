package com.example.connect5_project.bean;

import com.example.connect5_project.models.FieldDailyAvailability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DailyAvailabilityBeanOut {
    // Potrei aggiungere un response con gestione del risultato nella GUI
    Map<String, ArrayList<String>> weatherByHour;
    FieldDailyAvailability dailyAvailability;

    public Map<String, ArrayList<String>> getWeatherByHour() {
        return weatherByHour;
    }

    public void setWeatherByHour(Map<String, ArrayList<String>> weatherByHour) {  // Vediamo se qui HashMap va bene a Sonar
        this.weatherByHour = weatherByHour;
    }

    public FieldDailyAvailability getDailyAvailability() {
        return dailyAvailability;
    }

    public void setDailyAvailability(FieldDailyAvailability dailyAvailability) {
        this.dailyAvailability = dailyAvailability;
    }
}
