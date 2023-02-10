package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.Login;
import com.example.connect5_project.bean.LoginBeanIn;
import com.example.connect5_project.bean.LoginBeanOut;

import java.util.Scanner;

public class LoginCLI {
    Scanner console;
    public void main() {
        while (true) {
            String email = "";
            String password = "";
            System.out.println("Insert your email");
            LoginBeanIn beanIn = new LoginBeanIn();
            while (true) {
                email = console.nextLine();
                if (email.equals("")) {
                    System.out.println("Field email cannot be empty.\n\nInsert your email");  //NOSONAR
                    continue;
                }
                else if (email.equals("back")){
                    return;
                }
                if (!beanIn.setEmail(email)) {
                    System.out.println("You have not entered a correct email..\n\nInsert your email");  //NOSONAR
                }
                else
                    break;
            }
            while (true) {
                System.out.println("Insert password");  //NOSONAR
                password = console.nextLine();
                if (password.equals("")) {
                    System.out.println("Field password cannot be empty.\n\nInsert your email");  //NOSONAR
                } else
                    break;
            }
            beanIn.setPassword(password);
            Login loginController = new Login();
            LoginBeanOut bean_out = new LoginBeanOut();
            bean_out = loginController.loginVerify(beanIn);
            switch (bean_out.getResponse()) {
                case ("Email not registeres") -> {
                    System.out.println("Your emeil is not registered"); //NOSONAR
                }
                case ("Password incorrect") -> {
                    System.out.println("Password is incorrect");  //NOSONAR
                }
                case ("Error") -> {
                    System.out.println("We are working to resolve some problems.\nTry later.");  //NOSONAR
                }
                case ("MATCH") -> {
                    LoggedCLI logged = new LoggedCLI();
                    console.close();
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
