package com.example.connect5_project.boundary;

import com.example.connect5_project.bean.WeatherApiBeanResponse;
import com.example.connect5_project.bean.WeatherApiBeanRequest;
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
    public WeatherApiBeanResponse weitherCity(WeatherApiBeanRequest requestBean) {
        WeatherApiBeanResponse responseBean = new WeatherApiBeanResponse();
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

        JsonNode weather = this.weatherService(requestBean.getCity(), key, requestBean.getGapDay()+1);

        responseBean.setWeather(weather);
        responseBean.setResponse(true);
        return responseBean;
    }


    public JsonNode weatherService(String city, String key, int daysNumber) {
        try {
            //Da ora userò weatherapi.com
            OkHttpClient client = new OkHttpClient();

            Request req = new Request.Builder().url("http://api.weatherapi.com/v1/forecast.json?key=" + key + "&q=" + city + "&days=" + daysNumber + "&aqi=no&alerts=no").build();

            Response resp = client.newCall(req).execute();
            if (resp.body() != null) {
                String jsonData = resp.body().string();
                ObjectMapper mapper = new ObjectMapper();
                ObjectNode node = mapper.readValue(jsonData, ObjectNode.class);
                return node.get("forecast").get("forecastday").get(daysNumber - 1);
            } else {
                return null;
            }

        } catch (IOException e) {
            return null;
        }
    }

}
