package com.example.connect_five;

import com.example.connect_five.models.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class FirstScreen extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Connect5.fxml"));
        // Cancellata e messe le 3 righe sopra...Parent root = FXMLLoader.load(getClass().getResource("/Connect5.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(FirstScreen.class.getResource("/Connect5.fxml"));
        //Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        ArrayList<User> listaUtenti = new ArrayList<>();
        User user1 = new User("Federico", "Farinella", "federicofarinella@mymail.com", "myPassword", "fede");
        listaUtenti.add(user1);
        User user11 = listaUtenti.get(0);
        System.out.println(user11.getFirstName());
        stage.setTitle("Connect 5");
        stage.setScene(new Scene(root));
        stage.show();
        stage.setResizable(false);
    }


    public static void main(String[] args) {
        launch();
    }
}