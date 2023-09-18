package com.example.connect5_project.take_booking.view.CLI;

import com.example.connect5_project.take_booking.controller.BookingController;
import com.example.connect5_project.take_booking.model.SportCenter;
import com.example.connect5_project.utility.SharedStateSingletonCLI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SportCentersResultsCLI {
    private Scanner console;
    private BookingController controller;
    private List<SportCenter> centerResults;
    private Map<String, SportCenter> centersResults;

    public void run() {
        while (!SharedStateSingletonCLI.getInstance().isRedirecting()) {
            int i;
            String str = "";
            String choose = "";
            int size = centersResults.size();

            System.out.println("Search results:\n");
            if(size == 0) {
                System.out.println("Results not found for ypur search.");
            }

            for (i = 1; i < size + 1; i++) {
                str = Integer.toString(i);
                System.out.println(str + "- Name: " + centersResults.get(str).getName() + ".\n   City: " + centersResults.get(str).getCity());

            }
            System.out.println("\nOr type back or exit.");
            choose = this.getConsole().nextLine();
            switch (choose) {
                case "back"-> {
                    return;
                }
                case "exit"-> {
                    System.exit(0);
                }
            }

            int selectedNumber;
            // parseInt potrebbe sollevare eccezione...
            try {
                selectedNumber = Integer.parseInt(choose) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Insert a valid numeric data.\n\n");
                continue;
            }
            if (selectedNumber > size - 1) {
                System.out.println("Error: enter one of the numbers listed above\n");
                continue;
        }

            SportCenter choosenCenter = this.getCentersResults().get(choose);
            this.getController().setChoosenCenter(choosenCenter.getName());
            ChooseBookingDataCLI controlCli = new ChooseBookingDataCLI();
            controlCli.setScanner(console);
            controlCli.setController(controller);
            controlCli.run();

            //System.out.println("Center choosen: " + controller.getChoosenCenter().getName());  //NOSONAR





        }
    }


    public Scanner getConsole() {
        return this.console;
    }

    public void setScanner(Scanner scanner) {
        this.console = scanner;
    }

    public BookingController getController() {
        return this.controller;
    }

    public void setController(BookingController controller) {
        this.controller = controller;
    }

    public List<SportCenter> getCenterResults() {
        return centerResults;
    }

    public void setCenterResults(List<SportCenter> centerResults) {
        this.centerResults = centerResults;
    }



    public void setCentersResults(List<SportCenter> centerResultsArray) {
        centersResults = new HashMap<>();
        int i = 1;
        for (SportCenter centroSportivo : centerResultsArray) {
            centersResults.put(Integer.toString(i), centroSportivo);
            i++;
        }
        //this.centersResults = centersResults;
    }

    public Map<String, SportCenter> getCentersResults() {
        return centersResults;
    }
}
