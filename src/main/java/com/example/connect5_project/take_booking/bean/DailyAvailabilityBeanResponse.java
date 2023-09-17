package com.example.connect5_project.take_booking.bean;

import com.example.connect5_project.take_booking.model.FieldDailyAvailability;

import java.util.ArrayList;
import java.util.Map;


public class DailyAvailabilityBeanResponse {
    // Potrei aggiungere un response con gestione del risultato nella GUI
    private Map<String, ArrayList<String>> weatherByHour;
    private FieldDailyAvailability dailyAvailability;

    public DailyAvailabilityBeanResponse(WeatherApiBeanResponse weatherResponse) {
        this.weatherByHour = weatherResponse.getWeatherByHour();
    }

    public Map<String, ArrayList<String>> getWeatherByHour() {
        return weatherByHour;
    }


    public void setWeatherByHour(WeatherApiBeanResponse weatherResponseBean) {  // Vediamo se qui HashMap va bene a Sonar
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
