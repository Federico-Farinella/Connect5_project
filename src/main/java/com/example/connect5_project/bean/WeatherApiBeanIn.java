package com.example.connect5_project.bean;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherApiBeanIn {
    boolean response;
    String respDescription;
    //JsonNode weather;
    Map<String, ArrayList<String>> weatherByHour;

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

    public WeatherApiBeanIn() {
        this.weatherByHour = new HashMap<>();
        response = false;
    }

    public void setWeather(JsonNode weather) {
        List<String> array = new ArrayList<>();
        if (weather!=null) {
            response = true;
            int i;
            for (i = 15; i < 23; i++) {
                System.out.println(weather.get("hour").get(i).get("condition").get("text").toString());
                array.add(weather.get("hour").get(i).get("condition").get("text").toString());
                array.add(weather.get("hour").get(i).get("is_day").toString());
                weatherByHour.put(Integer.toString(i), new ArrayList<String>(array)); // se faccio put(i, array) in hashmap, viene inserito il riferimento all' oggetto
                array.clear();
            }
        }
        System.out.println("Fine: " + weatherByHour.get(Integer.toString(15)));
    }
}

