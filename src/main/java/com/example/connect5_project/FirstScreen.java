package com.example.connect5_project;

import com.example.connect5_project.models.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

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


    public static void main(String[] args) {
        launch();
    }
}