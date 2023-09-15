package com.example.connect5_project.bean;

import com.example.connect5_project.models.FieldDailyAvailability;

import java.util.ArrayList;
import java.util.Map;


public class DailyAvailabilityBeanOut {
    // Potrei aggiungere un response con gestione del risultato nella GUI
    private Map<String, ArrayList<String>> weatherByHour;
    private FieldDailyAvailability dailyAvailability;

    public DailyAvailabilityBeanOut(WeatherApiBeanIn weatherResponse) {
        this.weatherByHour = weatherResponse.getWeatherByHour();
    }

    public Map<String, ArrayList<String>> getWeatherByHour() {
        return weatherByHour;
    }


    public void setWeatherByHour(WeatherApiBeanIn weatherResponseBean) {  // Vediamo se qui HashMap va bene a Sonar
        this.weatherByHour = weatherResponseBean.getWeatherByHour();
    }

    public FieldDailyAvailability getDailyAvailability() {
        return this.dailyAvailability;
    }

    public Map<String, String> getDayAvailability() {
        return this.dailyAvailability.getDailyAvailability();
    }

    public void setDailyAvailability(FieldDailyAvailability dailyAvailability) {
        this.dailyAvailability = dailyAvailability;
    }
}
