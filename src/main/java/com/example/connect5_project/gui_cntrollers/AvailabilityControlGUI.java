package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.bean.DailyAvailabilityBeanOut;
import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AvailabilityControlGUI {
    @FXML
    private ImageView im15to16;
    @FXML
    private ImageView im16to17;
    @FXML
    private ImageView im17to18;
    @FXML
    private ImageView im18to19;
    @FXML
    private ImageView im19to20;
    @FXML
    private ImageView im20to21;
    @FXML
    private ImageView im21to22;
    @FXML
    private ImageView im22to23;
    @FXML
    private Label lab_center_name;
    @FXML
    private Label lab_date;

    @FXML
    private Label avail15;
    @FXML
    private Label avail16;
    @FXML
    private Label avail17;
    @FXML
    private Label avail18;
    @FXML
    private Label avail19;
    @FXML
    private Label avail20;
    @FXML
    private Label avail21;
    @FXML
    private Label avail22;

    @FXML
    private Button btnReserve15;
    @FXML
    private Button btnReserve16;
    @FXML
    private Button btnReserve17;
    @FXML
    private Button btnReserve18;
    @FXML
    private Button btnReserve19;
    @FXML
    private Button btnReserve20;
    @FXML
    private Button btnReserve21;
    @FXML
    private Button btnReserve22;


    private Navigate navigate;

    BookingController bookingController;

    private List<ImageView> images;
    private List<Label> hourAvailability;
    private List<Button> buttonsToReserve;

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

    public void setBookingController(BookingController booking_controller) {
        this.bookingController = booking_controller;
    }

    public void setImages (DailyAvailabilityBeanOut beanOut) {
        // Sonarcloud mi ha fatto sostituire hashMap con Map

        Map<String, ArrayList<String>> weatherResponse = beanOut.getWeatherByHour();
        int i = 15;
        URL resource;
        String condition;
        String is_day;
        for (ImageView im : images) {
            System.out.println(weatherResponse.get(Integer.toString(i)));
            condition = weatherResponse.get(Integer.toString(i)).get(0);
            is_day = weatherResponse.get(Integer.toString(i)).get(1);
            System.out.println("Tempo ora " + i + ": " + condition);
            switch (condition) {
                case ("\"Sunny\"") -> {
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

    public void setAvailability(DailyAvailabilityBeanOut beanOut) {
        Map<String, String> dailyAvailability = beanOut.getDailyAvailability().getDailyAvailability();
        int i = 15;
        int j = 0;
        for (Label lab : hourAvailability) {
            if (dailyAvailability.get(Integer.toString(i)).equals("0"))
                lab.setText("Available");
            else {
                lab.setText("Not available");
                buttonsToReserve.get(j).setVisible(false);
                buttonsToReserve.get(j).setDisable(true);
            }
            i++;
            j++;
        }
    }

    public synchronized void choosenBookingHour(ActionEvent event) throws IOException {
        Button typedButton = (Button) event.getSource();
        //Devo sistemare da qui!!!!!!!!!!!!!!!!!!!!!!!
        switch (typedButton.getId()) {
            case ("btnReserve15") ->
                    bookingController.setChoosenHour("15");
            case ("btnReserve16") ->
                    bookingController.setChoosenHour("16");
            case ("btnReserve17") ->
                    bookingController.setChoosenHour("17");
            case ("btnReserve18") ->
                    bookingController.setChoosenHour("18");
            case ("btnReserve19") ->
                    bookingController.setChoosenHour("19");
            case ("btnReserve20") ->
                    bookingController.setChoosenHour("20");
            case ("btnReserve21") ->
                    bookingController.setChoosenHour("21");
            case ("btnReserve22") ->
                    bookingController.setChoosenHour("22");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SetBookingOptional.fxml"));
        Parent root = loader.load();
        SetBookingOptionalGUI controlOptionalGui = loader.getController();
        controlOptionalGui.setBookingController(bookingController);
        controlOptionalGui.setLabCenterName(bookingController.getChoosenCenter().getName());
        controlOptionalGui.setLabDate(bookingController.getChoosenDate());
        controlOptionalGui.setLabHour(bookingController.getChoosenHour() + ".00");
        navigate.pushPage(((Node) event.getSource()).getScene());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()+1);
        controlOptionalGui.setNavigate(navigate);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));


        /*switch (typedButton.getId()) {
            case ("btnReserve15") ->
                    booking_controller.takeBooking("15");
            case ("btnReserve16") ->
                    booking_controller.takeBooking("16");
            case ("btnReserve17") ->
                    booking_controller.takeBooking("17");
            case ("btnReserve18") ->
                    booking_controller.takeBooking("18");
            case ("btnReserve19") ->
                    booking_controller.takeBooking("19");
            case ("btnReserve20") ->
                    booking_controller.takeBooking("20");
            case ("btnReserve21") ->
                    booking_controller.takeBooking("21");
            case ("btnReserve22") ->
                    booking_controller.takeBooking("22");
        }*/

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

        hourAvailability = new ArrayList<>();
        hourAvailability.add(avail15);
        hourAvailability.add(avail16);
        hourAvailability.add(avail17);
        hourAvailability.add(avail18);
        hourAvailability.add(avail19);
        hourAvailability.add(avail20);
        hourAvailability.add(avail21);
        hourAvailability.add(avail22);

        buttonsToReserve = new ArrayList<>();
        buttonsToReserve.add(btnReserve15);
        buttonsToReserve.add(btnReserve16);
        buttonsToReserve.add(btnReserve17);
        buttonsToReserve.add(btnReserve18);
        buttonsToReserve.add(btnReserve19);
        buttonsToReserve.add(btnReserve20);
        buttonsToReserve.add(btnReserve21);
        buttonsToReserve.add(btnReserve22);


    }

    public void back(ActionEvent e) throws Exception {
        /*Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        System.out.println(window);
        window.setScene(navigate.getPages().lastElement());
        navigate.pages.pop();*/
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        System.out.println(window);
        //navigate.pages.pop();
        window.setScene(navigate.getPages().lastElement());
        navigate.pages.pop();
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()-1);
    }

    public void home(ActionEvent e) throws Exception {
        /*navigate.pages.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        Stage window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));*/
        Stage window;
        int currentPagesAfterLogin = navigate.getCountPagesAfterLogin();
        for (int i = 0; i < currentPagesAfterLogin-1 ; i++) {
            navigate.getPages().pop();
        }
        navigate.setCountPagesAfterLogin(0);
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(navigate.getPages().lastElement());
        navigate.getPages().pop();
    }
}
