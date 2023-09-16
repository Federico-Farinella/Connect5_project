package com.example.connect5_project.boundary;

import com.example.connect5_project.bean.WeatherApiBeanIn;
import com.example.connect5_project.bean.WeatherApiBeanOut;
import com.example.connect5_project.weather_service.WeatherService;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class WeatherBoundary {
    public WeatherApiBeanIn weitherCity(WeatherApiBeanOut bean_out) {
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

        WeatherService service = new WeatherService();
        JsonNode weather = service.weatherService(bean_out.getCity(), key, bean_out.getGapDay()+1);

        responseBean.setWeather(weather);
        responseBean.setResponse(true);
        return responseBean;
    }
}
