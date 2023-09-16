package com.example.connect5_project.boundary;

import com.example.connect5_project.bean.WeatherApiBeanIn;
import com.example.connect5_project.bean.WeatherApiBeanOut;
import com.example.connect5_project.weather_service.WeatherService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WeatherBoundary {
    public WeatherApiBeanIn weitherCity(WeatherApiBeanOut requestBean) {
        WeatherApiBeanIn responseBean = new WeatherApiBeanIn();
        String configFilePath = "src/main/resources/config_weather.properties";
        Properties prop;
        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            prop = new Properties();
            prop.load(propsInput);
        } catch (FileNotFoundException e) {
            responseBean.setResponse(false);
            responseBean.setRespDescription("API configuration's file not found");
            return responseBean;
        } catch (IOException e) {
            responseBean.setResponse(false);
            responseBean.setRespDescription("Error loading API configuration's file");
            return responseBean;
        }

        String key = prop.getProperty("key");

        //WeatherService service = new WeatherService();
        //JsonNode weather = service.weatherService(requestBean.getCity(), key, requestBean.getGapDay()+1);
        JsonNode weather = this.weatherService(requestBean.getCity(), key, requestBean.getGapDay()+1);

        responseBean.setWeather(weather);
        responseBean.setResponse(true);
        return responseBean;
    }


    public JsonNode weatherService(String city, String key, int days_number) {
        try {
            //Da ora userò weatherapi.com
            OkHttpClient client = new OkHttpClient();

            Request req = new Request.Builder().url("http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + city + "&days=" + days_number + "&aqi=no&alerts=no").build();

            Response resp = client.newCall(req).execute();
            assert resp.body() != null;
            String jsonData = resp.body().string();
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode node = mapper.readValue(jsonData, ObjectNode.class);
            return node.get("forecast").get("forecastday").get(days_number-1);

        } catch (IOException e) {
            // Inserisci una mia eccezione qui per completezza e lanciala magari...
            return null;
        }
    }

}
