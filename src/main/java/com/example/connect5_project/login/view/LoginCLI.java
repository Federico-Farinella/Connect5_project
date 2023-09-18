package com.example.connect5_project.login.view;

import com.example.connect5_project.login.bean.LoginBeanRequest;
import com.example.connect5_project.login.controller.LoginController;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.login_exceptions.EmailNotRegisteredException;
import com.example.connect5_project.take_booking.view.cli.LoggedCLI;

import java.util.*;

public class LoginCLI {
    Scanner console;

    public void run() throws Exception { // riga 57 leggi altre cli devo gestire eccezioni al posto di responsedao
        List<String> passwordIsOk;
        mainLoop:
        while (true) {
            String email;
            System.out.println("Insert your email\n\nOr type back or exit.");
            LoginBeanRequest beanIn = new LoginBeanRequest();
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
                } else {
                    passwordIsOk = this.insertPassword();
                    if (passwordIsOk.get(0).equals("-1")) {
                        continue mainLoop;
                    } else {
                        String passwordInsered = passwordIsOk.get(0);
                        beanIn.setPassword(passwordInsered);
                        break;
                    }

                }
            }
            LoginController loginController = new LoginController();
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
                controlLoggedCLI.run();
            }
        }

    }

    public List<String> insertPassword() {
        List<String> passwordIsOk = new ArrayList<>();
        String password;
        boolean loop = true;
        System.out.println("Insert password\n\nOr type back or exit.\n");  //NOSONAR
        while (loop) {
            password = console.nextLine();
            switch (password) {
                case "" -> {
                    System.out.println("Field password cannot be empty.\n\nInsert your password\n\nOr type back or exit.");  //NOSONAR
                }
                case "back" -> {
                passwordIsOk.add(0, "-1");
                loop = false;
                }
                case "exit" -> {
                    System.exit(0);
                }
                default -> {
                    passwordIsOk.add(0, password);
                    loop = false;
                }
            }

        }
        return passwordIsOk;
    }

    public Scanner getConsole() {
        return console;
    }

    public void setScanner(Scanner scanner) {
        this.console = scanner;
    }
}
