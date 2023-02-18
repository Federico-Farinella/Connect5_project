package com.example.connect5_project.controllers;

import com.example.connect5_project.bean.*;
import com.example.connect5_project.boundary.WeatherBoundary;
import com.example.connect5_project.dao.BookingDao;
import com.example.connect5_project.dao.DailiAvailabilityDao;
import com.example.connect5_project.dao.SportCenterDAO;
import com.example.connect5_project.models.Booking;
import com.example.connect5_project.models.CentroSportivo;
import com.example.connect5_project.models.FieldDailyAvailability;
import com.example.connect5_project.models.User;
import com.example.connect5_project.models.bookings_decorator.ConcreteBasic;
import com.example.connect5_project.models.bookings_decorator.Optional;
import com.example.connect5_project.utility.CurrentUser;

import java.time.LocalDate;
import java.util.List;

public class BookingController {
    private List<CentroSportivo> centersResultsList;
    private CentroSportivo choosenCenter;
    private LocalDate choosenDate;
    private String choosenHour;




    public SearchResultBeanOut searchCenters(SearchResultsBeanIn bean_in) {
        String search_mode = bean_in.getSearchMode();
        System.out.println("Booking Controller, searvhCenters. Print search_mode: " + search_mode);
        SearchResultBeanOut bean_to = new SearchResultBeanOut();
        SportCenterDAO cDao = new SportCenterDAO();
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
        centersResultsList = bean_to.getListOfCenters();
        return bean_to;
    }

    public DailyAvailabilityBeanOut getDailyWeather(DailyAvailabilityBeanIn beanIn) {
        choosenDate = beanIn.getDateToSearch();
        WeatherApiBeanOut beanWeatherOut = new WeatherApiBeanOut();
        beanWeatherOut.setGapDay(beanIn.getDateToSearch());
        beanWeatherOut.setCity(choosenCenter.getCity());
        WeatherBoundary weatherBoundary = new WeatherBoundary();
        WeatherApiBeanIn beanWeatherIn; //  = new WeatherApiBeanIn();
        beanWeatherIn = weatherBoundary.weitherCity(beanWeatherOut);

        //DailiAvailabilityDao dao = new DailiAvailabilityDao();
        //FieldDailyAvailability dailyAvailability = dao.dbSearchAvailability(choosenCenter, beanIn.getDateToSearch());


        System.out.println("Booking Controller analyze beanWeatherIn returned: " + beanWeatherIn.getWeatherByHour());
        DailyAvailabilityBeanOut bean_out = new DailyAvailabilityBeanOut();
        bean_out.setWeatherByHour(beanWeatherIn.getWeatherByHour());
        System.out.println("BookingController: " + bean_out.getWeatherByHour().get(Integer.toString(15)));
        return bean_out;
    }

    public FieldDailyAvailability getDailyAvailability(DailyAvailabilityBeanIn beanIn) {
        DailiAvailabilityDao dao = new DailiAvailabilityDao();
        this.choosenDate = beanIn.getDateToSearch();
        return dao.dbSearchAvailability(choosenCenter, choosenDate);
    }

    public boolean confirmBooking(boolean withReferee, boolean withTunics) {
        User user = CurrentUser.getInstance().getUser();
        Booking booking = new Booking(choosenCenter, user, choosenDate, choosenHour);
        if (withReferee)
            booking.setWithReferee();
        if (withTunics)
            booking.setWithReferee();

        BookingDao dao = new BookingDao();
        String ret = dao.saveBooking(booking);
        System.out.println("Booking Controller: return della DaoBooking: " + ret);
        return ret.equals("Booking registered");

    }

    public void setCentersResultsList(List<CentroSportivo> centersResultsList) {
        this.centersResultsList = centersResultsList;
    }


    public CentroSportivo getChoosenCenter() {
        return choosenCenter;
    }


    public void setChoosenCenter(String name) {
        for (CentroSportivo center : centersResultsList) {
            if (center.getName().equals(name)) {
                choosenCenter = center;
                System.out.println("Choosen center : " + choosenCenter.getName());
            }
        }
    }

    public LocalDate getChoosenDate() {
        return choosenDate;
    }

    public String getChoosenHour() {
        return choosenHour;
    }

    public void setChoosenHour(String choosenHour) {
        this.choosenHour = choosenHour;
    }
}
