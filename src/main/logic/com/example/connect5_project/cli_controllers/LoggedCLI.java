package com.example.connect5_project.cli_controllers;

import java.util.Scanner;

public class LoggedCLI {
    Scanner console;

    public void main() throws Exception {  // Anche qui (riga 25) gestire l eccezione che parte dalla sportcenterdao al posto del responsebean
        while (true) {
            System.out.println("1- Search for sport center\n2- Manage your bookings\n\n\tOr type back or exit.");
            String choose = "";
            while (true) {
                choose = console.nextLine();
                if (choose.equals("1") || choose.equals("2") || choose.equals("back") || choose.equals("exit"))
                    break;
                System.out.println("Error reading your input.\n1- Search for sport center\n2- Manage your bookings\n" +
                        "\n\nOr type back or exit.");   //NOSONAR
            }
            switch (choose) {
                case ("1") -> {
                    SearchSportCentersCLI searchCenterCli = new SearchSportCentersCLI();
                    searchCenterCli.setScanner(console);
                    System.out.println("Hai scelto di eseguire il login");  //NOSONAR
                    //console.close();
                    searchCenterCli.main();
                    //login.main();
                }
                //Devo inserire case 2 manage bookings
                case ("back") -> {
                    return;
                }
                case ("exit") -> {
                    System.exit(0);
                }
            }
        }
    }

    public void setScanner(Scanner scanner) {
        this.console = scanner;
    }
}
