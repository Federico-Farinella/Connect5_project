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
            for (i = 1; i < size + 1; i++) {
                str = Integer.toString(i);
                System.out.println(str + "- Name: " + centersResults.get(str).getName() + ".\n   City: " + centersResults.get(str).getCity());

            }
            choose = console.nextLine();
            i = Integer.parseInt(choose) - 1;
            if (i > size - 1) {
                System.out.println("Error: enter one of the numbers listed above\n");
                continue;
        }

            CentroSportivo choosenCenter = centersResults.get(choose);
            controller.setChoosenCenter(choosenCenter.getName());
            System.out.println("Center choosen: " + controller.getChoosenCenter().getName());  //NOSONAR





        }
    }

    public void setScanner(Scanner scanner) {
        this.console = scanner;
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
