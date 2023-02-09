package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class AvailabilityControlGUI {
    @FXML
    ImageView im15to16;
    @FXML
    ImageView im16to17;
    @FXML
    ImageView im17to18;
    @FXML
    ImageView im18to19;
    @FXML
    ImageView im19to20;
    @FXML
    ImageView im20to21;
    @FXML
    ImageView im21to22;
    @FXML
    ImageView im22to23;
    @FXML
    Label lab_center_name;
    @FXML
    Label lab_date;

    Navigate navigate;

    BookingController booking_controller;
    private ArrayList<ImageView> images ;

    public Label getLabCenterName() {
        return lab_center_name;
    }

    public void setLabCenterName(String lab_center_name) {
        this.lab_center_name.setText(lab_center_name);
    }

    public Label getLabDate() {
        return lab_date;
    }

    public void setLabDate(String lab_date) {
        this.lab_date.setText(lab_date);
    }

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void setBooking_controller(BookingController booking_controller) {
        this.booking_controller = booking_controller;
    }

    public void setImages (Map<String, ArrayList<String>> weatherResponse) {
        // Sonarcloud mi ha fatto sostituire hashMap con Map
        int i = 15;
        URL resource;
        String condition;
        String is_day;
        for (ImageView im : images) {
            System.out.println(i);
            System.out.println(weatherResponse.get(Integer.toString(i)));
            //resource = null;
            condition = weatherResponse.get(Integer.toString(i)).get(0);
            is_day = weatherResponse.get(Integer.toString(i)).get(1);
            System.out.println("Tempo ora " + i + ": " + condition);
            switch (condition) {
                case ("\"Sunny\"") -> { // Continua da qui bisogna sistemare le classi e metodi per ottenere un HashMap in weather e manipolare immagini da visualizzare
                    System.out.println("Ok");
                    resource = getClass().getResource("/images_weather/Sun.png");
                    System.out.println("Yes");
                }
                case ("\"Clear\"") -> {
                    System.out.println("I'm in Clear case");
                    resource = getClass().getResource("/images_weather/Moon.png");
                }
                case ("\"Partly cloudy\"") -> {
                    if (Objects.equals(is_day, "1")) {
                        resource = getClass().getResource("/images_weather/Sun&Clouds.png");
                    } else {
                        resource = getClass().getResource("/images_weather/Moon_Clouds.png");
                    }
                }
                case ("\"Overcast\"") -> resource = getClass().getResource("/images_weather/Overcast.png");
                case ("\"Cloudy\"") -> resource = getClass().getResource("/images_weather/Clouds_Right.png");

                case ("\"Patchy rain possible\"") -> {
                    if (Objects.equals(is_day, "1")) {
                        resource = getClass().getResource("/images_weather/PatchyRain_Day.png");
                    } else {
                        resource = getClass().getResource("/images_weather/Moon&Rain.png");
                        //resource = getClass().getResource("/images/Rain.png");
                    }
                }
                case ("\"Patchy light drizzle\""), ("\"Light drizzle\"") ->  //|| ("\"Light drizzle\"")): {
                        resource = getClass().getResource("/images_weather/Rain.png");

                default -> resource = null;
                // Mi manca , pioggia leggera da sostituire con quella che ho messo Rain_4 che sarà pioggia pesante?, temporale, neve
            }
            if (resource != null) {
                Image image = new Image(resource.toString());
                im.setImage(image);
            }
            i++;
        }
    }

    public void initialize() {
        images = new ArrayList<>();
        images.add(im15to16);
        images.add(im16to17);
        images.add(im17to18);
        images.add(im18to19);
        images.add(im19to20);
        images.add(im20to21);
        images.add(im21to22);
        images.add(im22to23);
        //lab_center_name.setText(booking_controller.getChoosenCenter().getName());
        //lab_date.setText(booking_controller);
    }

    public void back(ActionEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        System.out.println(window);
        //navigate.pages.pop();
        window.setScene(navigate.getPages().lastElement());
        navigate.pages.pop();
        //History.pagine.pop();
    }

    public void home(ActionEvent e) throws Exception {
        Stage window;
        //History.pagine.clear();
        navigate.pages.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
