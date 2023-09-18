package com.example.connect5_project.take_booking.view.cli;

import com.example.connect5_project.take_booking.bean.DailyAvailabilityBeanRequest;
import com.example.connect5_project.take_booking.bean.DailyAvailabilityBeanResponse;
import com.example.connect5_project.take_booking.controller.BookingController;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.DailyAvailabilityException;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.utility.BusinessConstants;
import com.example.connect5_project.utility.SharedStateSingletonCLI;

import java.time.LocalDate;
import java.util.*;

public class ChooseBookingDataCLI {
    private Scanner console;
    private BookingController controller;

    public void run() {
        while(!SharedStateSingletonCLI.getInstance().isRedirecting()) {
            System.out.println("Center choosen: " + controller.getChoosenCenter().getName() + "\n" +
                    "Choose a date from those proposed.\n");

            int daysAfter = BusinessConstants.getDayToChoose();
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

            System.out.println("\nOr type back or exit.");

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
                 System.out.println("Insert a valid numeric input.\n\n");
                 continue;
             }
            if ((chooseInt < daysAfter+1 && chooseInt >= 1)) {
                //this.getController().setChoosenDate(dates.get(choose));
                DailyAvailabilityBeanRequest beanRequest = new DailyAvailabilityBeanRequest();
                beanRequest.setDateToSearch(dates.get(choose));
                //DailyAvailabilityBeanOut beanResponse = this.getController().getDailyWeather(beanRequest);
                DailyAvailabilityBeanResponse beanResponse;
                try {
                    beanResponse = this.getController().getDailyAvailability(beanRequest);
                } catch (ConnectionDBException e0) {
                    System.out.println("We are working to resolve some problems.\nTry later.");
                    continue;
                } catch (DailyAvailabilityException e1) {
                    System.out.println("---NO DAILY AVAILABILITY---\n\n");
                    continue;
                } catch (MyException e2) {
                    System.out.println("System under maintenance.\nTry later.");
                    continue;
                }
                AvailabilityControlCLI availabilityControlCLI = new AvailabilityControlCLI();
                availabilityControlCLI.setBookingController(this.getController());
                availabilityControlCLI.setConsole(this.getScanner());
                availabilityControlCLI.run(beanResponse);

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
