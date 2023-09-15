package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.bean.SearchResultBeanOut;
import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.models.CentroSportivo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SportCentersResultsCLI {
    private Scanner console;
    private BookingController controller;
    private List<CentroSportivo> centerResults;
    private Map<String, CentroSportivo> centersResults;

    public void main() {
        while (true) {
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

            CentroSportivo choosenCenter = this.getCentersResults().get(choose);
            this.getController().setChoosenCenter(choosenCenter.getName());
            ChooseBookingDataCLI controlCli = new ChooseBookingDataCLI();
            controlCli.setScanner(console);
            controlCli.setController(controller);
            controlCli.main();

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

    public List<CentroSportivo> getCenterResults() {
        return centerResults;
    }

    public void setCenterResults(List<CentroSportivo> centerResults) {
        this.centerResults = centerResults;
    }



    public void setCentersResults(List<CentroSportivo> centerResultsArray) {
        centersResults = new HashMap<>();
        int i = 1;
        for (CentroSportivo centroSportivo : centerResultsArray) {
            centersResults.put(Integer.toString(i), centroSportivo);
            i++;
        }
        //this.centersResults = centersResults;
    }

    public Map<String, CentroSportivo> getCentersResults() {
        return centersResults;
    }
}
