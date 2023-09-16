package com.example.connect5_project;

import com.example.connect5_project.cli_controllers.Connect5CLI;
import com.example.connect5_project.models.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class FirstScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        stage.setTitle("Connect 5");
        URL iconPath = getClass().getResource("/images/pallaMuro.jpg");
        if (iconPath != null)
            stage.getIcons().add(new Image(iconPath.toString()));
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
    }


    public static void main(String[] args) throws Exception {  // anche qui riga 35 gestire eccezione
        if (args.length == 0) {
            launch();
        } else {
            Connect5CLI firstScreen = new Connect5CLI();
            firstScreen.main();
        }
    }
}