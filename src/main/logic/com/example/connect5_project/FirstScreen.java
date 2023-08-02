package com.example.connect5_project;

import com.example.connect5_project.cli_controllers.Connect5CLI;
import com.example.connect5_project.models.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FirstScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        stage.setTitle("Connect 5");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
    }


    public static void main(String[] args) throws Exception {  // anche qui riga 35 gestire eccezione

        //if (!args[0].equals("0")) {
        if (args.length == 0) {
            launch();
        } else {
            Connect5CLI firstScreen = new Connect5CLI();
            firstScreen.main();
        }
    }
}