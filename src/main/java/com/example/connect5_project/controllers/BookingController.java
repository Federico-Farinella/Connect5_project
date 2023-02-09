package com.example.connect5_project.controllers;

import com.example.connect5_project.bean.*;
import com.example.connect5_project.boundary.WeatherBoundary;
import com.example.connect5_project.dao.CentriSportiviDAO;
import com.example.connect5_project.models.CentroSportivo;

import java.util.ArrayList;
import java.util.List;

public class BookingController {
    private List<CentroSportivo> centers_results_list;
    private CentroSportivo choosen_center;

    /*public void setCentersResultsList(ArrayList<CentroSportivo> centers_results_list) {
        this.centers_results_list = centers_results_list;
    }*/

    public CentroSportivo getChoosenCenter() {
        return choosen_center;
    }

    public void setChoosenCenter(String name) {
        //this.choosen_center = choosen_center;
        for (CentroSportivo center : centers_results_list) {
            if (center.getName().equals(name)) {
                choosen_center = center;
                System.out.println("Choosen center : " + choosen_center.getName());
            }
        }
    }

    public SearchResultBeanOut searchCenters(SearchResultsBeanIn bean_in) {
        String search_mode = bean_in.getSearch_mode();
        SearchResultBeanOut bean_to = new SearchResultBeanOut();
        CentriSportiviDAO cDao = new CentriSportiviDAO();
        switch (search_mode) {
            case ("Name") -> {
                bean_to = cDao.dbSearchCentersByName(bean_in.getName());
            }
            case ("City") -> {
                bean_to = cDao.dbSearchCentersByCity(bean_in.getCity());
            }
            case ("Name and city") -> {
                bean_to = cDao.dbSearchCenters(bean_in.getName(), bean_in.getCity());
            }
        }
        centers_results_list = bean_to.getListOfCenters();
        return bean_to;
    }

    public DailyAvailabilityBeanOut getAvailability(DailyAvailabilityBeanIn bean_in) {
        WeatherApiBeanOut bean_weather_out = new WeatherApiBeanOut();
        bean_weather_out.setGapDay(bean_in.getDateToSearch());
        bean_weather_out.setCity(choosen_center.getCity());
        WeatherBoundary weather_boundary = new WeatherBoundary();
        WeatherApiBeanIn bean_weather_in = new WeatherApiBeanIn();
        bean_weather_in = weather_boundary.weitherCity(bean_weather_out);
        System.out.println("Booking Controller analyze bean_weather_in returned: " + bean_weather_in.getWeatherByHour());
        DailyAvailabilityBeanOut bean_out = new DailyAvailabilityBeanOut();
        bean_out.setWeatherByHour(bean_weather_in.getWeatherByHour());
        System.out.println("BookingController: " + bean_out.getWeatherByHour().get(Integer.toString(15)));
        return bean_out;
        //weather_boundary.weitherCity(bean_weather_out);
    }
}
