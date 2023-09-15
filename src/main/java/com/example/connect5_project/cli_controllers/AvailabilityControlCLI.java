package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.bean.DailyAvailabilityBeanOut;
import com.example.connect5_project.controllers.BookingController;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class AvailabilityControlCLI {
    private Scanner console;
    private BookingController bookingController;

    public void main(DailyAvailabilityBeanOut beanOut) {
        Map<String, String> dailyAvailability = beanOut.getDayAvailability();
        Map<String, ArrayList<String>> weatherResponse = beanOut.getWeatherByHour();
        String message = "Availability of center: " + this.getBookingController().getChoosenCenter().getName() +
                "\nCity: " + this.getBookingController().getChoosenCenter().getCity() +
                "\nDate: " + this.getBookingController().getChoosenDate() + "\n";
        while (true) {

        }
    }

    public void setAvailabilityAndWeather(DailyAvailabilityBeanOut beanOut) {
        //Questo metodo controlla ora per ora se il campo è disponibile. Se non lo è ("1"), non verrà abilitata la possibilità
        // di prenotare il campo in quell'ora
        //Map<String, String> dailyAvailability = beanOut.getDailyAvailability().getDailyAvailability();
        Map<String, String> dailyAvailability = beanOut.getDayAvailability();
        Map<String, ArrayList<String>> weatherResponse = beanOut.getWeatherByHour();

    }

    public Scanner getConsole() {
        return console;
    }

    public void setConsole(Scanner console) {
        this.console = console;
    }

    public BookingController getBookingController() {
        return bookingController;
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }
}
