package com.example.connect5_project.bean;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;

public class WeatherApiBeanIn {
    boolean response;
    String resp_description;
    JsonNode weather;
    HashMap<String, ArrayList<String>> weather_by_hour;

    public boolean getResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public String getRespDescription() {
        return resp_description;
    }

    public void setRespDescription(String resp_description) {
        this.resp_description = resp_description;
    }

    public HashMap<String, ArrayList<String>> getWeatherByHour() {
        return weather_by_hour;
    }

    public void setWeatherByHour(HashMap<String, ArrayList<String>> weather_by_hour) {
        this.weather_by_hour = weather_by_hour;
    }

    public WeatherApiBeanIn() {
        this.weather_by_hour = new HashMap<>();
        response = false;
    }

    public void setWeather(JsonNode weather) {
        ArrayList<String> array = new ArrayList<>();
        //HashMap<String, String> weather_for_h = new HashMap<>();
        //Map<String, String> weather_for_h= null;
        //weather_for_hour.put("Ciao", "Bello");
        //System.out.println();
        if (weather!=null) {
            response = true;
            int i;
            for (i = 15; i < 23; i++) {
                //System.out.println(weather);
                System.out.println(weather.get("hour").get(i).get("condition").get("text").toString());
                array.add(weather.get("hour").get(i).get("condition").get("text").toString());
                array.add(weather.get("hour").get(i).get("is_day").toString());
                //System.out.println(array);
                weather_by_hour.put(Integer.toString(i), new ArrayList<String>(array)); // se faccio put(i, array) in hashmap, viene inserito il riferimento all' oggetto
                System.out.println("Ora stampo l' array");                              // quindi dopo la array.clear non ci sarà più il vecchio valore di array in hashmap
                System.out.println(weather_by_hour.get(Integer.toString(i)));
                array.clear();
            }
        }
        System.out.println("Fine: " + weather_by_hour.get(Integer.toString(15)));


        //this.weather_for_hour = weather;
        // Continua da qui devo ritornare una Map formata da coppia ora: tempo(description Json Node)
    }
}

