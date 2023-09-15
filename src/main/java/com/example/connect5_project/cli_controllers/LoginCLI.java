package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.LoginController;
import com.example.connect5_project.bean.LoginBeanIn;
import com.example.connect5_project.bean.LoginBeanOut;

import java.util.Scanner;

public class LoginCLI {
    Scanner console;

    public void main() throws Exception { // riga 57 leggi altre cli devo gestire eccezioni al posto di responsedao
        mainLoop:
        while (true) {
            String email = "";
            String password = "";
            System.out.println("Insert your email\n\nOr type back or exit.");
            LoginBeanIn beanIn = new LoginBeanIn();
            while (true) {
                email = console.nextLine();
                switch (email) {
                    case "":
                        System.out.println("Field email cannot be empty.\nInsert your email\n\nOr type back or exit.");  //NOSONAR

                        continue;
                    case "back":
                        return;
                    case "exit":
                        System.exit(0);
                }
                if (!beanIn.setEmail(email)) {
                    System.out.println("You have not entered a correct email..\n\nInsert your email\n\nOr type back or exit.");  //NOSONAR
                } else
                    break;
            }
            label:
            while (true) {
                System.out.println("Insert password\n\nOr type back or exit.");  //NOSONAR
                password = console.nextLine();
                switch (password) {
                    case "":
                        System.out.println("Field password cannot be empty.\n\nInsert your password\n\nOr type back or exit.");  //NOSONAR

                        break;
                    case "back":
                        continue mainLoop;
                    case "exit":
                        System.exit(0);
                    default:
                        break label;
                }
            }
            beanIn.setPassword(password);
            LoginController loginController = new LoginController();
            LoginBeanOut bean_out = new LoginBeanOut();
            bean_out = loginController.loginVerify(beanIn);
            switch (bean_out.getResponse()) {
                case ("Email not registered") -> {
                    System.out.println("Your emeil is not registered"); //NOSONAR
                }
                case ("Password incorrect") -> {
                    System.out.println("Password is incorrect");  //NOSONAR
                }
                case ("Error") -> {
                    System.out.println("We are working to resolve some problems.\nTry later.");  //NOSONAR
                }
                case ("Match") -> {
                    LoggedCLI logged = new LoggedCLI();
                    logged.setScanner(console);
                    logged.main();
                }
            }
        }
        //Ho già il Current user impostato dal login

    }

    public void setScanner(Scanner scanner) {
        this.console = scanner;
    }
}
