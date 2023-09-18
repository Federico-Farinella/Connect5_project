module com.example.connect5_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires okhttp3;
    requires mysql.connector.java;


    opens com.example.connect5_project to javafx.fxml;
    exports com.example.connect5_project;
    exports com.example.connect5_project.gui_cntrollers;
    opens com.example.connect5_project.gui_cntrollers to javafx.fxml;
    exports com.example.connect5_project.bean;
    opens com.example.connect5_project.bean to javafx.fxml;
    exports com.example.connect5_project.history;
    exports com.example.connect5_project.weather_service;
    exports com.example.connect5_project.take_booking.model.bookingsTypeDecorator;
    exports com.example.connect5_project.exceptions;
    exports com.example.connect5_project.exceptions.login_exceptions;
    exports com.example.connect5_project.main;
    opens com.example.connect5_project.main to javafx.fxml;
    exports com.example.connect5_project.login.view;
    opens com.example.connect5_project.login.view to javafx.fxml;
    exports com.example.connect5_project.login.controller;
    opens com.example.connect5_project.login.controller to javafx.fxml;
    exports com.example.connect5_project.take_booking.view.gui;
    opens com.example.connect5_project.take_booking.view.gui to javafx.fxml;
    exports com.example.connect5_project.take_booking.model;
    opens com.example.connect5_project.take_booking.model to javafx.fxml;
    opens com.example.connect5_project.take_booking.model.bookingsTypeDecorator to javafx.fxml;
    exports com.example.connect5_project.utility;
    opens com.example.connect5_project.utility to javafx.fxml;
    exports com.example.connect5_project.take_booking.controller;
    opens com.example.connect5_project.take_booking.controller to javafx.fxml;
    exports com.example.connect5_project.take_booking.bean;
    opens com.example.connect5_project.take_booking.bean to javafx.fxml;
    exports com.example.connect5_project.login.bean;
    opens com.example.connect5_project.login.bean to javafx.fxml;
}