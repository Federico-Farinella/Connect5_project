package com.example.connect5_project.take_booking.view.CLI;

import com.example.connect5_project.take_booking.bean.SearchResultBeanResponse;
import com.example.connect5_project.take_booking.bean.SearchResultsBeanRequest;
import com.example.connect5_project.take_booking.controller.BookingController;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.utility.SharedStateSingletonCLI;

import java.util.Scanner;

public class SearchCenterModeCLI {
    private Scanner console;
    private BookingController controller;

    public void search(String choose) throws Exception {  // devo togliere throws exception e gestirla (riga 77)
        SearchResultsBeanRequest beanIn = new SearchResultsBeanRequest();
        SearchResultBeanResponse responseBean;
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
                                break label1;
                        }
                    }
                }
            }


            this.setController(new BookingController());
            beanIn = new SearchResultsBeanRequest();
            beanIn.setCli(searchType, field);
            try {
                responseBean = this.getController().searchCenters(beanIn);
            } catch (MyException e) {
                System.out.println("Error connecting data\nWe are working to resolve problem\nTry later.");
                continue;
            }

            if (responseBean.getListOfCenters().isEmpty()) {
                System.out.println("Not found centers with your inputs.");  //NOSONAR
                continue;
            }

            // Qui sotto sostituire con eccezione!!!!
            /*else if (responseBean.getDaoResponse().equals("Error data")) {
                System.out.println("Error connecting data\nWe are working to resolve problem\nTry later.");  //NOSONAR
                continue;
            }*/



            SportCentersResultsCLI resultsCli = new SportCentersResultsCLI();
            resultsCli.setScanner(console);
            resultsCli.setCentersResults(responseBean.getListOfCenters());
            resultsCli.setController(controller);
            resultsCli.main();
        }


    }

    public SearchResultsBeanRequest searchByName() {
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
            SearchResultsBeanRequest beanIn = new SearchResultsBeanRequest();
            beanIn.setName(name);
            return beanIn;
    }
    public void setScanner(Scanner scanner) {
        this.console = scanner;
    }

    public BookingController getController() {
        return controller;
    }

    public void setController(BookingController controller) {
        this.controller = controller;
    }

}
