package com.example.connect5_project.take_booking.view.cli;

import com.example.connect5_project.take_booking.model.FootballPlayer;
import com.example.connect5_project.take_booking.model.CurrentUser;

import java.util.Scanner;

public class LoggedCLI {
    Scanner console;

    public void run() throws Exception {  // Anche qui (riga 25) gestire l eccezione che parte dalla sportcenterdao al posto del responsebean
        while (true) {
            FootballPlayer user = CurrentUser.getInstance().getUser();
            System.out.println("----- Welcome " + user.getNickName() + " -----\n");

            System.out.println("1- Search for sport center\n2- Manage your bookings\n\n\tOr type back or exit.");
            String choose;
            while (true) {
                choose = console.nextLine();
                if (choose.equals("1") || choose.equals("2") || choose.equals("back") || choose.equals("exit"))
                    break;
                System.out.println("""
                        Error reading your input.
                        1- Search for sport center
                        2- Manage your bookings


                        Or type back or exit.""");   //NOSONAR
            }
            switch (choose) {
                case ("1") -> {
                    SearchSportCentersCLI searchCenterCli = new SearchSportCentersCLI();
                    searchCenterCli.setScanner(console);
                    System.out.println("Hai scelto di eseguire il login");  //NOSONAR
                    //console.close();
                    searchCenterCli.run();
                    //login.main();
                }
                //Devo inserire case 2 manage bookings
                case ("back") -> {
                    return;
                }
                case ("exit") ->
                    System.exit(0);
            }
        }
    }

    public Scanner getConsole() {
        return console;
    }

    public void setScanner(Scanner scanner) {
        this.console = scanner;
    }

}
