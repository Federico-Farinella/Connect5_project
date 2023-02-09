package com.example.connect5_project.weather_service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


import java.io.IOException;

public class WeatherService {
    public JsonNode weatherService(String city, String key, int days_number) {
        try {
            //Da ora userò weatherapi.com

            OkHttpClient client = new OkHttpClient();
            System.out.println("City:" + city + ", key: " + key + ", Gap day: " + days_number);

            Request req = new Request.Builder().url("http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + city + "&days=" + days_number + "&aqi=no&alerts=no").build();

            Response resp = client.newCall(req).execute();
            assert resp.body() != null;
            String jsonData = resp.body().string();
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.readValue(jsonData, ObjectNode.class);
            System.out.println("Ora stampo weather da WeatherService: " + node.get("forecast").get("forecastday").get(days_number));
            return node.get("forecast").get("forecastday").get(days_number-1);

        } catch (IOException e) {
            return null;
        }
    }
}
