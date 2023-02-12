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
    exports com.example.connect5_project.models;
    opens com.example.connect5_project.models to javafx.fxml;
    exports com.example.connect5_project.bean;
    opens com.example.connect5_project.bean to javafx.fxml;
    exports com.example.connect5_project.models.booking_Decorator;
    opens com.example.connect5_project.models.booking_Decorator to javafx.fxml;
    exports com.example.connect5_project.history;
    exports com.example.connect5_project.controllers;
    exports com.example.connect5_project.weather_service;
}