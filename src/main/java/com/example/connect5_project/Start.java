package com.example.connect5_project;

import com.example.connect5_project.main.FirstScreenCLI;
import com.example.connect5_project.utility.BusinessConstants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = loader.load();
        stage.setTitle("Connect 5");
        URL iconPath = getClass().getResource("/images/pallaMuro.jpg");
        if (iconPath != null)
            stage.getIcons().add(new Image(iconPath.toString()));
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
    }


        public static void main(String[] args) throws Exception {
            BusinessConstants.initialize();
        if (args.length == 0) {
            launch();
        } else {
            FirstScreenCLI firstScreen = new FirstScreenCLI();
            firstScreen.main();
        }
    }
}