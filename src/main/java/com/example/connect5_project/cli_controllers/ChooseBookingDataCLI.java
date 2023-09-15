package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.bean.DailyAvailabilityBeanIn;
import com.example.connect5_project.bean.DailyAvailabilityBeanOut;
import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.utility.BusinessConstants;

import java.time.LocalDate;
import java.util.*;

public class ChooseBookingDataCLI {
    private Scanner console;
    private BookingController controller;

    public void main() {
        while(true) {
            System.out.println("Center choosen: " + controller.getChoosenCenter().getName() + "\n" +
                    "Choose a date from those proposed.\n");

            int daysAfter = BusinessConstants.DAY_TO_CHOOSE;
            int i;
            Map<String, LocalDate> dates = new HashMap<>();
            List<LocalDate> date = new ArrayList<>();
            String key;
            for (i=0; i<daysAfter+1; i++) {
                //date.add(LocalDate.now().plusDays(i));
                key = String.valueOf(i+1);
                dates.put(key, LocalDate.now().plusDays(i));
                System.out.println(key + "- " + dates.get(key));
            }

            String choose = this.getScanner().nextLine();

            switch (choose) {
                case "back"-> {
                    return;
                }
                case "exit"-> {
                    System.exit(0);
                }
            }

            int chooseInt;
             try {
                 chooseInt = Integer.parseInt(choose);
             } catch (NumberFormatException e) {
                 System.out.println("Insert a valid numeric data.\n\n");
                 continue;
             }
            if ((chooseInt < daysAfter+1 && chooseInt >= 1)) {
                //this.getController().setChoosenDate(dates.get(choose));
                DailyAvailabilityBeanIn beanRequest = new DailyAvailabilityBeanIn();
                beanRequest.setDateToSearch(dates.get(choose));
                DailyAvailabilityBeanOut beanResponse = this.getController().getDailyWeather(beanRequest);
                AvailabilityControlCLI availabilityControlGUI = new AvailabilityControlCLI();
                availabilityControlGUI.setBookingController(this.getController());
                availabilityControlGUI.setConsole(this.getScanner());

            } else {
                System.out.println("Choose one of the options shown.\n\n");
            }


        }
    }

    public Scanner getScanner() {
        return console;
    }

    public void setScanner(Scanner console) {
        this.console = console;
    }

    public BookingController getController() {
        return controller;
    }

    public void setController(BookingController controller) {
        this.controller = controller;
    }
}
