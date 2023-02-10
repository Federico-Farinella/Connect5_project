package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.bean.SearchResultBeanOut;
import com.example.connect5_project.bean.SearchResultsBeanIn;
import com.example.connect5_project.controllers.BookingController;

import java.util.Scanner;

public class SearchCenterModeCLI {
    private Scanner console;
    private BookingController controller;
    private String choose;

    public void search(String choose) {
        SearchResultsBeanIn beanIn = new SearchResultsBeanIn();
        SearchResultBeanOut bean_out = new SearchResultBeanOut();
        while (true) {
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
            if (bean_out.getDaoResponse().equals("Not match")) {
                System.out.println("Not found centers with your inputs.");  //NOSONAR
                continue;
            }
            else if (bean_out.getDaoResponse().equals("Error data")) {
                System.out.println("Error connecting data\nWe are working to resolve problem\nTry later.");  //NOSONAR
                continue;
            }



            SportCentersResultsCLI resultsCli = new SportCentersResultsCLI();
            resultsCli.setScanner(console);
            resultsCli.setCentersResults(bean_out.getListOfCenters());
            System.out.println("SerchCenterModeCLI: bean_out results:" + bean_out.getListOfCenters().get(0));  //NOSONAR
            System.out.println("SerchCenterModeCLI: resultCli results:" + resultsCli.getCentersResults().get("1").getName());  //NOSONAR
            //controller.setCentersResultsList(bean_out.getListOfCenters());
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

    public void setChoose(String choose) {
        this.choose = choose;
    }
}
