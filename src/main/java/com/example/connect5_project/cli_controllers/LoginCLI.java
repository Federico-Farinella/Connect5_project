package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.controllers.LoginController;
import com.example.connect5_project.bean.LoginBeanIn;
import com.example.connect5_project.bean.LoginBeanOut;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.login_exceptions.EmailNotRegisteredException;

import java.util.Scanner;

public class LoginCLI {
    Scanner console;

    public void main() throws Exception { // riga 57 leggi altre cli devo gestire eccezioni al posto di responsedao
        boolean loop;
        mainLoop:
        while (true) {
            String email;
            String password = "";
            System.out.println("Insert your email\n\nOr type back or exit.");
            LoginBeanIn beanIn = new LoginBeanIn();
            loop = true;
            while (loop) {
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
                } else {
                    loop = false;
                }
            }
            loop = true;

            while (loop) {
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
                    default:  {
                        loop = false;
                    }
                }

            }
            beanIn.setPassword(password);
            LoginController loginController = new LoginController();
            LoginBeanOut bean_out = new LoginBeanOut();
            //bean_out = loginController.loginVerify(beanIn);
            boolean isLogged;
            try {
                isLogged = loginController.loginVerify(beanIn);
            } catch (ConnectionDBException e) {
                System.out.println("We are working to resolve some problems.\nTry later.");
                continue;
            } catch (EmailNotRegisteredException e) {
                System.out.println("Your emeil is not registered");
                continue;
            }

            if (!isLogged) {
                System.out.println("Password is not correct.\n");
            } else {
                LoggedCLI controlLoggedCLI = new LoggedCLI();
                controlLoggedCLI.setScanner(this.getConsole());
                controlLoggedCLI.main();
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
