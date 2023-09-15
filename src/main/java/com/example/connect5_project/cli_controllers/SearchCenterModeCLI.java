package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.bean.SearchResultBeanOut;
import com.example.connect5_project.bean.SearchResultsBeanIn;
import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.utility.SharedStateSingletonCLI;

import java.util.Scanner;

public class SearchCenterModeCLI {
    private Scanner console;
    private BookingController controller;
    //private String choose;

    public void search(String choose) throws Exception {  // devo togliere throws exception e gestirla (riga 77)
        SearchResultsBeanIn beanIn = new SearchResultsBeanIn();
        SearchResultBeanOut bean_out;
        while (!SharedStateSingletonCLI.getInstance().isRedirecting()) {
            String field = "";
            String searchType = "";
            switch (choose) {

                case ("1") -> {
                    beanIn.setSearchMode("name");
                    System.out.println("Type center's name\n\nOr type back or exit.");  //NOSONAR
                    String name = "";
                    label:
                    while (true) {
                        name = console.nextLine();
                        switch (name) {
                            case "":
                                System.out.println("Field center's name cannot be empty.\nType center's name\n\nOr type back or exit.");  //NOSONAR
                                break;
                            case "back":
                                return;
                            case "exit":
                                System.exit(0);
                            default:
                                field = name;
                                searchType = "Name";
                                System.out.println("SearchCenterModeCLI, searchType: " + searchType + " Field: " + field);
                                break label;
                        }
                    }
                }
                case ("2") -> {
                    beanIn.setSearchMode("city");
                    System.out.println("Type city\n\nOr type back or exit.");  //NOSONAR
                    String city = "";
                    label1:
                    while (true) {
                        city = console.nextLine();
                        switch (city) {
                            case "":
                                System.out.println("Field city cannot be empty.\nType center's name\n\nOr type back or exit.");  //NOSONAR
                                break;
                            case "back":
                                return;
                            case "exit":
                                System.exit(0);
                            default:
                                field = city;

                                searchType = "City";
                                System.out.println("SearchCenterModeCLI, searchType: " + searchType + " Field: " + field);  //MOSONAR
                                break label1;
                        }
                    }
                }
            }


            controller = new BookingController();
            beanIn = new SearchResultsBeanIn();
            //System.out.println("SearchCenterModeCLI, searchType: " + beanIn.getSearchMode());
            beanIn.setCli(searchType, field);
            System.out.println("2-SearchCenterModeCLI, searchType: " + beanIn.getSearchMode() + " Field: " + field);
            bean_out = controller.searchCenters(beanIn);

            if (bean_out.getListOfCenters().getSportCentersSearchResults().isEmpty()) {
                System.out.println("Not found centers with your inputs.");  //NOSONAR
                continue;
            }

            /*if (bean_out.getDaoResponse().equals("Not match")) {
                System.out.println("Not found centers with your inputs.");  //NOSONAR
                continue;
            }*/
            // Qui sotto sostituire con eccezione!!!!
            else if (bean_out.getDaoResponse().equals("Error data")) {
                System.out.println("Error connecting data\nWe are working to resolve problem\nTry later.");  //NOSONAR
                continue;
            }



            SportCentersResultsCLI resultsCli = new SportCentersResultsCLI();
            resultsCli.setScanner(console);
            resultsCli.setCentersResults(bean_out.getListOfCenters().getSportCentersSearchResults());
            resultsCli.setController(controller);
            resultsCli.main();
        }


    }

    public SearchResultsBeanIn searchByName() {
            System.out.println("Type center's name\n\nOr type back or exit.");  //NOSONAR
            String name = "";
            label:
            while (true) {
                name = console.nextLine();
                switch (name) {
                    case "":
                        System.out.println("Field center's name cannot be empty.\nType center's name\n\nOr type back or exit.");  //NOSONAR
                        break;
                    case "back":
                        return null;
                    case "exit":
                        System.exit(0);
                    default:
                        break label;
                }
            }
            SearchResultsBeanIn beanIn = new SearchResultsBeanIn();
            beanIn.setName(name);
            return beanIn;
    }
    public void setScanner(Scanner scanner) {
        this.console = scanner;
    }

    public void setController(BookingController controller) {
        this.controller = controller;
    }

}
