package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.utility.SharedStateSingletonCLI;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class BookingResponseCLI {
    private boolean response;
    private BookingController bookingController;

    public void main() {
        //while (!SharedStateSingletonCLI.getInstance().isRedirecting()) {
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
                //return;
            }

            //SharedStateSingletonCLI.getInstance().setRedirecting(true);
            /*Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        SharedStateSingletonCLI.getInstance().setRedirecting(true);
                    });
                }
            };*/
            //timer.schedule(task, 5000);

        //}
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
