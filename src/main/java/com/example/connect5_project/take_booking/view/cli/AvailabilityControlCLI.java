package com.example.connect5_project.take_booking.view.cli;

import com.example.connect5_project.take_booking.bean.DailyAvailabilityBeanResponse;
import com.example.connect5_project.take_booking.controller.BookingController;
import com.example.connect5_project.utility.SharedStateSingletonCLI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AvailabilityControlCLI {
    private Scanner console;
    private BookingController bookingController;

    public void run(DailyAvailabilityBeanResponse beanOut) {
        /*if (beanOut.getDailyAvailability() == null) {
            System.out.println("Insert a valid numeric input.\n\n");
        }*/

        Map<String, String> dailyAvailability = beanOut.getDayAvailability();
        Map<String, ArrayList<String>> weatherResponse = beanOut.getWeatherByHour();
        String message = "Availability of center: " + this.getBookingController().getChoosenCenter().getName() +
                "\nCity: " + this.getBookingController().getChoosenCenter().getCity() +
                "\nDate: " + this.getBookingController().getChoosenDate() + "\nBase price: " +
                this.getBookingController().getChoosenCenter().getFieldPrice() +"\n";
        System.out.println(message);
        int size = dailyAvailability.size();

        while (!SharedStateSingletonCLI.getInstance().isRedirecting()) {
            int i;
            List<String> list = new ArrayList<>();
            String hourAvailability;
            System.out.println("Availability and forecast weather description: \n");

                int j = 0;
                for (i = 15; i < 15 + size; i++) {
                    hourAvailability = String.valueOf(i);
                    if (dailyAvailability.get(hourAvailability).equals("0")) {
                        list.add(j + 1 + ") " + i + "-" + String.valueOf(i + 1) + ": Available.\n" +
                                "Weather description: " + weatherResponse.get(hourAvailability).get(0) + "\n    _________");

                        System.out.println(list.get(j));
                        j++;
                    }
                }

                if (list.size() == 0) {
                    System.out.println("No availability for this date\n" +
                            "Type back or exit.");
                } else {

                    System.out.println("\nOr type back or exit.");
                }
                String choose = this.getConsole().nextLine();

                switch (choose) {
                    case "back" -> {
                        return;
                    }
                    case "exit" -> {
                        System.exit(0);
                    }
                }
                int chooseToInt;
                try {
                    chooseToInt = Integer.parseInt(choose);
                } catch (NumberFormatException e) {
                    System.out.println("Insert a valid numeric input.\n\n");
                    continue;
                }

                String choosenHour;

                if (chooseToInt <= list.size() && chooseToInt >= 1) {
                    choosenHour = list.get(chooseToInt - 1).substring(3, 5);
                    System.out.println("Choosen our: " + choosenHour);
                    this.getBookingController().setChoosenHour(choosenHour);
                    SetBookingOptionalCLI setBookingOptionaControllerCLI = new SetBookingOptionalCLI();
                    setBookingOptionaControllerCLI.setBookingController(this.getBookingController());
                    setBookingOptionaControllerCLI.setConsole(this.getConsole());
                    setBookingOptionaControllerCLI.chooseOptional();


                } else {
                    System.out.println("Choose one of the options shown.\n\n");
                }
        }
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
