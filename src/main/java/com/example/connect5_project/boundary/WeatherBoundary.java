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
        WeatherApiBeanIn bean_in = new WeatherApiBeanIn();
        String configFilePath = "src/main/resources/config_weather.properties";
        //FileInputStream propsInput;
        Properties prop;
        System.out.println("Inizio metodo weitherCity API2");
        try (FileInputStream propsInput = new FileInputStream(configFilePath)) {
            //propsInput = new FileInputStream(configFilePath);
            prop = new Properties();
            prop.load(propsInput);
        } catch (FileNotFoundException e) {
            //configFilePath = "config1.properties";
            bean_in.setResponse(false);
            bean_in.setRespDescription("API configuration's file not found");
            return bean_in;
        } catch (IOException e) {
            bean_in.setResponse(false);
            bean_in.setRespDescription("Error loading API configuration's file");
            return bean_in;
        }

        String key = prop.getProperty("key");

        //Properties prop = new Properties();
        /*try {
            prop.load(propsInput);
        } catch (IOException e) {
            e.printStackTrace();
            bean_in.setResponse(false);
            bean_in.setRespDescription("Error loading Api's calling properties");
            return bean_in;
        }
        String key = prop.getProperty("key");
        System.out.println(key);*/

        WeatherService service = new WeatherService();
        System.out.println("Dati da mandare al Weather service: Gap day, City: " + bean_out.getGap_day() + ", " + bean_out.getCity());
        JsonNode weather = service.weatherService(bean_out.getCity(), key, bean_out.getGap_day()+1);
        System.out.println("WeatherBoundary stampo JsonNode di ritorno weather: " + weather);

        //WeatherApiBeanFrom bean_in = new WeatherApiBeanFrom();
        bean_in.setWeather(weather);
        System.out.println("Fine metodo weitherCity API2");
        //bean_in.setResponse(true);
        return bean_in;
    }
}
