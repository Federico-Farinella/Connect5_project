package com.example.connect5_project.take_booking.view.cli;

import com.example.connect5_project.utility.SharedStateSingletonCLI;

import java.util.Scanner;

public class SearchSportCentersCLI {
    private Scanner console;

    public void run() throws Exception {
        while (!SharedStateSingletonCLI.getInstance().isRedirecting()) {
            System.out.println("1- Search by center's name\n2- Search by city\n\n\tOr type back or exit.");  //NOSONAR
            String choose = "";

            while (true) {
                choose = console.nextLine();
                if (choose.equals("1") || choose.equals("2") || choose.equals("back") || choose.equals("exit"))
                    break;
                System.out.println("Error reading your input.\n1- Search by center's name\n2- Search by city\n" +
                        "\n\nOr type back or exit.");   //NOSONAR
            }

            SearchCenterModeCLI searchModeCli;

            switch (choose) {
                case ("1") -> {
                    searchModeCli = new SearchCenterModeCLI();
                    searchModeCli.setScanner(console);
                    System.out.println("You have chosen to search by name");  //NOSONAR
                    searchModeCli.search(choose);
                }

                case ("2") -> {
                    searchModeCli = new SearchCenterModeCLI();
                    searchModeCli.setScanner(console);
                    System.out.println("You have chosen to search by city");  //NOSONAR
                    searchModeCli.search(choose);
                }

                case ("back") -> {
                    return;
                }

                case ("exit") -> {
                    System.exit(0);
                }
                default -> {
                    System.out.println("Error reading your input.\n1- Search by center's name\n2- Search by city\n\nOr type back or exit.");
                }

            }

        }
    }

    public void setScanner(Scanner scanner) {
        this.console = scanner;
    }
}
