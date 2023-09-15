package com.example.connect5_project.controllers;

import com.example.connect5_project.bean.*;
import com.example.connect5_project.boundary.WeatherBoundary;
import com.example.connect5_project.dao.BookingDao;
import com.example.connect5_project.dao.DailiAvailabilityDao;
import com.example.connect5_project.dao.SportCenterDAO;
import com.example.connect5_project.exceptions.DbConnectException;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.exceptions.SportCenterException;
import com.example.connect5_project.exceptions.TakeBookingException;
import com.example.connect5_project.models.*;
import com.example.connect5_project.utility.CurrentUser;

import java.time.LocalDate;
import java.util.List;

public class BookingController {
    SportCentersSearchResults centersResultsList;
    //private List<CentroSportivo> centersResultsList;
    private CentroSportivo choosenCenter;
    private LocalDate choosenDate;
    private String choosenHour;




    public SearchResultBeanOut searchCenters(SearchResultsBeanIn bean_in) throws MyException {
        String search_mode = bean_in.getSearchMode();
        System.out.println("Booking Controller, searvhCenters. Print search_mode: " + search_mode);
        SearchResultBeanOut bean_to = new SearchResultBeanOut();
        SportCenterDAO cDao = new SportCenterDAO();
        try {
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
            this.setCentersResultsList(bean_to.getListOfCenters());

            return bean_to;

        } catch (SportCenterException e) {
            if (e.getMessage().equals("Not match"))
                throw e;
            else
                throw new SportCenterException("Error. Try later");
        }
    }

    public DailyAvailabilityBeanOut getDailyWeather(DailyAvailabilityBeanIn beanIn) {
        this.setChoosenDate(beanIn.getDateToSearch());
        //choosenDate = beanIn.getDateToSearch();
        WeatherApiBeanOut weatherRequestBean = new WeatherApiBeanOut();
        weatherRequestBean.setGapDay(beanIn.getDateToSearch());
        weatherRequestBean.setCity(getChoosenCenter().getCity());
        WeatherBoundary weatherBoundary = new WeatherBoundary();
        WeatherApiBeanIn weatherResponseBean; //  = new WeatherApiBeanIn();
        weatherResponseBean = weatherBoundary.weitherCity(weatherRequestBean);

        //DailiAvailabilityDao dao = new DailiAvailabilityDao();
        //FieldDailyAvailability dailyAvailability = dao.dbSearchAvailability(choosenCenter, beanIn.getDateToSearch());


        System.out.println("Booking Controller analyze weatherResponseBean returned: " + weatherResponseBean.getWeatherByHour());
        DailyAvailabilityBeanOut bean_out = new DailyAvailabilityBeanOut(weatherResponseBean);
        /*DailyAvailabilityBeanOut bean_out = new DailyAvailabilityBeanOut();
        bean_out.setWeatherByHour(weatherResponseBean);*/
        System.out.println("BookingController: " + bean_out.getWeatherByHour().get(Integer.toString(15)));
        return bean_out;
    }

    public DailyAvailabilityBeanOut getDailyAvailability(DailyAvailabilityBeanIn beanIn) throws MyException {
        this.setChoosenDate(beanIn.getDateToSearch());
        //choosenDate = beanIn.getDateToSearch();
        WeatherApiBeanOut weatherRequestBean = new WeatherApiBeanOut();
        weatherRequestBean.setGapDay(beanIn.getDateToSearch());
        weatherRequestBean.setCity(getChoosenCenter().getCity());
        WeatherBoundary weatherBoundary = new WeatherBoundary();
        WeatherApiBeanIn weatherResponseBean; //  = new WeatherApiBeanIn();
        weatherResponseBean = weatherBoundary.weitherCity(weatherRequestBean);

        //DailiAvailabilityDao dao = new DailiAvailabilityDao();
        //FieldDailyAvailability dailyAvailability = dao.dbSearchAvailability(choosenCenter, beanIn.getDateToSearch());


        System.out.println("Booking Controller analyze weatherResponseBean returned: " + weatherResponseBean.getWeatherByHour());
        DailyAvailabilityBeanOut bean_out = new DailyAvailabilityBeanOut(weatherResponseBean);
        /*DailyAvailabilityBeanOut bean_out = new DailyAvailabilityBeanOut();
        bean_out.setWeatherByHour(weatherResponseBean);*/
        System.out.println("BookingController: " + bean_out.getWeatherByHour().get(Integer.toString(15)));

        ////////////////
        DailiAvailabilityDao dao = new DailiAvailabilityDao();
        this.choosenDate = beanIn.getDateToSearch();
        FieldDailyAvailability dailyAvailability;
        try {
            dailyAvailability = dao.dbSearchAvailability(choosenCenter, choosenDate);
        } catch (DbConnectException e) {
            String messageToGuiController = "Error with accessing data. Please try later";
            throw new DbConnectException(messageToGuiController);
        }

        bean_out.setDailyAvailability(dailyAvailability);
        return bean_out;
        //return dailyAvailability;
    }

    public boolean confirmBooking(boolean withReferee, boolean withTunics) throws TakeBookingException {
        User user = CurrentUser.getInstance().getUser();
        Booking booking = new Booking(choosenCenter, user, choosenDate, choosenHour);
        if (withReferee)
            booking.setWithReferee();

        if (withTunics)
            booking.setWithTunics();

        BookingDao dao = new BookingDao();
        /*String ret = dao.saveBooking(booking);
        if (ret.equals("Booking registered"))
            dao.updateAvailability(booking);
        System.out.println("Booking Controller: return della DaoBooking: " + ret);
        return ret.equals("Booking registered");*/
        boolean ret1;
        try {
            ret1 = dao.saveBooking1(booking);
        } catch (TakeBookingException e) {
            System.out.println("Error accessing data. Please try later.");
            throw new TakeBookingException("Error accessing data. Please try later.");
        }
        System.out.println("Booking result: " + ret1);
        return ret1;

        /*try {
            BookingDao dao = new BookingDao();
            String ret = dao.saveBooking(booking);
            System.out.println("Booking Controller: return della DaoBooking: " + ret);
            return ret.equals("Booking registered");
        } catch ()*/
        // Continua da quiiiiii!!! Sostituisci codice sopra e fai in modo che se non viene correttamente preso
        // il booking dalla dao allora la dao della disponibilità dei campi non dovrà modificare il valore del campo di quella riga
        // della disponibilità del centro sportivo. Dai un occhiata anche alla SportCenterDao metodo dbSearchCenterByCity (2o try)

    }

    public void setCentersResultsList(SportCentersSearchResults centersResultsList) {
        this.centersResultsList = centersResultsList;
    }

    public void setHostingCenter(CentroSportivo sportCenter) {
        this.choosenCenter = sportCenter;
    }


    public CentroSportivo getChoosenCenter() {
        return choosenCenter;
    }


    public void setChoosenCenter(String name) {
        for (CentroSportivo center : centersResultsList.getSportCentersSearchResults()) {
            if (center.getName().equals(name)) {
                this.setHostingCenter(center);
                System.out.println("Choosen center : " + choosenCenter.getName());
            }
        }
    }

    public LocalDate getChoosenDate() {
        return choosenDate;
    }

    public void setChoosenDate(LocalDate date) {
        this.choosenDate = date;
    }

    public String getChoosenHour() {
        return choosenHour;
    }

    public void setChoosenHour(String choosenHour) {
        this.choosenHour = choosenHour;
    }
}
