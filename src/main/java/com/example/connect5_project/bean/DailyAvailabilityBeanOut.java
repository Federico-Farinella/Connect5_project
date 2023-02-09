package com.example.connect5_project.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DailyAvailabilityBeanOut {
    /*HashMap<String, ArrayList<String>> weatherByHour;

    public HashMap<String, ArrayList<String>> getWeatherByHour() {
        return weatherByHour;
    }

    public void setWeatherByHour(HashMap<String, ArrayList<String>> weather_by_hour) {
        this.weatherByHour = weather_by_hour;
    }*/
    Map<String, ArrayList<String>> weatherByHour;

    public Map<String, ArrayList<String>> getWeatherByHour() {
        return weatherByHour;
    }

    public void setWeatherByHour(HashMap<String, ArrayList<String>> weatherByHour) {  // Vediamo se qui HashMap va bene a Sonar
        this.weatherByHour = weatherByHour;
    }
}
