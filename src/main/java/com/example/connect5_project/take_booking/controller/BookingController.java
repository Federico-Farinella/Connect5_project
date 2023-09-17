package com.example.connect5_project.take_booking.controller;

import com.example.connect5_project.take_booking.bean.*;
import com.example.connect5_project.take_booking.boundary.WeatherBoundary;
import com.example.connect5_project.exceptions.*;
import com.example.connect5_project.take_booking.model.*;
import com.example.connect5_project.take_booking.model.bookingsType_decorator.FootballPlayer;
import com.example.connect5_project.utility.CurrentUser;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

public class BookingController {
    private SportCentersSearchResults centersResultsList;
    private SportCenter choosenCenter;
    private LocalDate choosenDate;
    private String choosenHour;




    public SearchResultBeanResponse searchCenters(SearchResultsBeanRequest bean_in) throws MyException {
        String search_mode = bean_in.getSearchMode();
        SearchResultBeanResponse beanResponse = new SearchResultBeanResponse();
        SportCenterDAO cDao = new SportCenterDAO();
        try {
            switch (search_mode) {
                case ("Name") -> {
                    beanResponse = cDao.dbSearchCentersByName(bean_in.getName());
                }
                case ("City") -> {

                    beanResponse = cDao.dbSearchCentersByCity(bean_in.getCity());
                }
                case ("Name and city") -> {
                    beanResponse = cDao.dbSearchCenters(bean_in.getName(), bean_in.getCity());
                }
            }
            this.setCentersResultsList(beanResponse.getSearchResults());

            return beanResponse;

        } catch (SportCenterException e) {
            if (e.getMessage().equals("Not match"))
                throw e;
            else
                throw new SportCenterException("Error. Try later");
        }
    }


    public DailyAvailabilityBeanResponse getDailyAvailability(DailyAvailabilityBeanRequest beanIn) throws MyException {
        this.setChoosenDate(beanIn.getDateToSearch());

        WeatherApiBeanRequest weatherRequestBean = new WeatherApiBeanRequest();
        weatherRequestBean.setGapDay(beanIn.getDateToSearch());
        weatherRequestBean.setCity(getChoosenCenter().getCity());
        WeatherBoundary weatherBoundary = new WeatherBoundary();
        WeatherApiBeanResponse weatherResponseBean;
        weatherResponseBean = weatherBoundary.weitherCity(weatherRequestBean);


        DailyAvailabilityBeanResponse bean_out = new DailyAvailabilityBeanResponse(weatherResponseBean);

        DailyAvailabilityDao dao = new DailyAvailabilityDao();
        this.setChoosenDate(beanIn.getDateToSearch());
        FieldDailyAvailability dailyAvailability;
        try {
            dailyAvailability = dao.dbSearchAvailability(this.getChoosenCenter(), this.getChoosenDate());
        } catch (ConnectionDBException e0) {
            String messageToGuiController = "Error with accessing data. Please try later";
            throw new ConnectionDBException(messageToGuiController);
        } catch (DailyAvailabilityException e1) {
            throw new DailyAvailabilityException(e1.getMessage());
        }

        bean_out.setDailyAvailability(dailyAvailability);
        return bean_out;
    }

    public boolean confirmBooking(boolean withReferee, boolean withTunics) throws MyException {
        FootballPlayer footballPlayer = CurrentUser.getInstance().getUser();
        Booking booking = new Booking(this.getChoosenCenter(), footballPlayer, this.getChoosenDate(), this.getChoosenHour());
        if (withReferee)
            booking.setWithReferee();

        if (withTunics)
            booking.setWithTunics();
        boolean isFileSystem;
        try {
            isFileSystem = this.isFileSystem();
        } catch (MyException e) {
            System.out.println("Sto lanciando la MyException1 da Booking Controller confirmBooking");
            throw new MyException("System error.");
        }
        BookingDao dao;
        if (!isFileSystem) {
            dao = new BookingDaoDb();
        } else {
            dao = new BookingDaoFs();
        }
        boolean ret1;
        try {
            ret1 = dao.saveBooking(booking);
        } catch (TakeBookingException e0) {
            throw new TakeBookingException("Error accessing data. Please try later.");
        } catch (ConnectionDBException e1) {
            throw new ConnectionDBException("Error connecting database. Please try later.");
        } catch (MyException e2) {
            System.out.println("Sto lanciando la MyException2 da Booking Controller confirmBooking");
            throw new MyException("System error.");
        }
        return ret1;

    }

    public void setCentersResultsList(SportCentersSearchResults centersResultsList) {
        this.centersResultsList = centersResultsList;
    }

    public void setHostingCenter(SportCenter sportCenter) {
        this.choosenCenter = sportCenter;
    }


    public SportCenter getChoosenCenter() {
        return choosenCenter;
    }


    public void setChoosenCenter(String name) {
        for (SportCenter center : centersResultsList.getSportCentersSearchResults()) {
            if (center.getName().equals(name)) {
                this.setHostingCenter(center);
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

    public boolean isFileSystem() throws MyException {
        boolean persistenceIsOnFS;
        try {
            FileInputStream propsInput = new FileInputStream("src/main/resources/config.properties");
            Properties prop = new Properties();
            prop.load(propsInput);
            String property = prop.getProperty("daoIsOnFileSystem");
            persistenceIsOnFS = (property.equals("true"));
            propsInput.close();
        } catch (IOException e0) {
            throw new MyException("System error.");
        }
        return persistenceIsOnFS;
    }
}
