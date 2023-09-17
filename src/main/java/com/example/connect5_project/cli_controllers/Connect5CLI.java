package com.example.connect5_project.cli_controllers;

import java.util.Scanner;

public class Connect5CLI {
    public void main() throws Exception { //anche qui riga 28 gestire eccezione sportcenterdao invece di responsebean
        while (true) {
            System.out.println("Welcome in Connect5 family!!!\nAre you signed in?\n" +
                    "1- Login\n2-Create account\n\nType the respective number and press enter");  //NOSONAR
            Scanner console = new Scanner(System.in);
            String choose = "";
            while (true) {
                choose = console.nextLine();
                if (choose.equals("1") || choose.equals("2") || choose.equals("exit"))
                    break;
                System.out.println("Error reading your input.\n1- Login\n2-Create account\n" +
                        "\nType the respective number and press enter");   //NOSONAR
            }
            switch (choose) {
                case ("1") -> {
                    LoginCLI login = new LoginCLI();
                    login.setScanner(console);
                    System.out.println("Hai scelto di eseguire il login");  //NOSONAR
                    //console.close();
                    login.execute();
                    //login.main();
                }
                case ("exit") -> {
                    System.exit(0);
                }
            }
        }
    }
}
