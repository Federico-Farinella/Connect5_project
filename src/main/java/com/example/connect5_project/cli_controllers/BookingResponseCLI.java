package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.utility.SharedStateSingletonCLI;

public class BookingResponseCLI {
    private boolean response;
    private BookingController bookingController;

    public void execute() {
            if (this.isResponse()) {
                System.out.println("Congratulations! Your booking has been successfull. You will be redirected to the home page in 5 seconds.\n");
            } else {
                System.out.println("We apologize for the inconvenience. We are working to resolve the issue. Try later. You will be redirected to the home page in 5 seconds.\n");
            }

            try {
                SharedStateSingletonCLI.getInstance().setRedirecting(true);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
    }

    public boolean isResponse() {
        return this.response;
    }


    public void setResponse(boolean respone) {
        this.response = respone;
    }

    public BookingController getBookingController() {
        return bookingController;
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }
}
