package com.example.connect5_project.take_booking.bean;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherApiBeanResponse {
    boolean response;
    private String respDescription;
    private Map<String, ArrayList<String>> weatherByHour;

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getRespDescription() {
        return respDescription;
    }

    public void setRespDescription(String respDescription) {
        this.respDescription = respDescription;
    }

    public Map<String, ArrayList<String>> getWeatherByHour() {
        return weatherByHour;
    }

    public void setWeatherByHour(Map<String, ArrayList<String>> weatherByHour) {
        this.weatherByHour = weatherByHour;
    }

    public WeatherApiBeanResponse() {
        this.weatherByHour = new HashMap<>();
        this.response = false;
    }

    public void setWeather(JsonNode weather) {
        List<String> array = new ArrayList<>();
        if (weather!=null) {
            this.setResponse(true);
            int i;
            for (i = 15; i < 23; i++) {
                array.add(weather.get("hour").get(i).get("condition").get("text").toString());
                array.add(weather.get("hour").get(i).get("is_day").toString());
                this.getWeatherByHour().put(Integer.toString(i), new ArrayList<>(array)); // se faccio put(i, array) in hashmap, viene inserito il riferimento all' oggetto
                array.clear();
            }
        }
    }
}

