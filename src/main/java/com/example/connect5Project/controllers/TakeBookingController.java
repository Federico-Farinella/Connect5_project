package com.example.connect5Project.controllers;

public class TakeBookingController {
    private static TakeBookingController controller = null;
    private Integer day;
    private Integer month;
    private Integer year;

    private TakeBookingController() {
    }

    public static TakeBookingController getController(){
        if (controller == null)
            controller = new TakeBookingController();
        return controller;
    }

    public void chosenBooking(Integer day, Integer month, Integer year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

}
