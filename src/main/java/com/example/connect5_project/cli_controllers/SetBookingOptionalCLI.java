package com.example.connect5_project.cli_controllers;

import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.exceptions.TakeBookingException;
import com.example.connect5_project.utility.SharedStateSingletonCLI;

import java.util.Scanner;

public class SetBookingOptionalCLI {
    private Scanner console;
    private BookingController bookingController;

    public SetBookingOptionalCLI() {

    }

    public void chooseOptional() {

        String choose;
        mainLoop: while (!SharedStateSingletonCLI.getInstance().isRedirecting()) {
            System.out.println("""
                    In accordance with our policy to raise young Italian referees, you can choose if you want the presence of an official referee.
                    It will cost you 40 euros more than the base price imposed by the sport center.

                    Type "y" if you require the presence of an official referee;
                    Type "n" if you don't require it.
                    """);

            System.out.println("\nOr type back or exit.");

            choose = this.getConsole().nextLine();

            boolean withReferee = false;
            switch (choose) {
                case "back" -> {
                    return;
                }
                case "exit" -> {
                    System.exit(0);
                }
                case "y" -> {
                    withReferee = true;
                }
                case "n" -> {

                }
                default -> {
                    System.out.println("Insert one of options shown.\n");
                    continue;
                }
            }

            System.out.println("""
                    Do you need gaming jerseys?.

                    Type "y" if you require jerseys;
                    Type "n" if you don't require it.
                    """);

            System.out.println("\nOr type back or exit.");

            choose = this.getConsole().nextLine();
            boolean withTunics = false;
            switch (choose) {
                case "back" -> {
                    continue mainLoop;
                }
                case "exit" -> {
                    System.exit(0);
                }
                case "y" -> {
                    withTunics = true;
                }
                case "n" -> {

                }
                default -> {
                    System.out.println("Insert one of options shown.\n");
                    continue;
                }
            }

            boolean isConfirmed;
            BookingResponseCLI bookingResponseControlCLI = new BookingResponseCLI();
            try {
                bookingController.confirmBooking(withReferee, withTunics);
                isConfirmed = true;
            } catch (TakeBookingException e0) {
                isConfirmed = false;
            } catch (ConnectionDBException e1) {
                isConfirmed = false;
            } catch (MyException e2) {
                isConfirmed = false;
            }
            bookingResponseControlCLI.setResponse(isConfirmed);
            bookingResponseControlCLI.main();
        }
    }

    public Scanner getConsole() {
        return console;
    }

    public void setConsole(Scanner console) {
        this.console = console;
    }

    public BookingController getBookingController() {
        return bookingController;
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }
}
