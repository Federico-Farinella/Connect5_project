package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.bean.DailyAvailabilityBeanIn;
import com.example.connect5_project.bean.DailyAvailabilityBeanOut;
import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseBookingDataGUI {

    private BookingController bookingController;
    private Navigate navigate;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label labelNoDate;

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public Navigate getNavigate() {
        return navigate;
    }

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void setBookingController(BookingController booking_controller) {
        this.bookingController = booking_controller;
    }

    public void search(ActionEvent e) throws IOException {
        if (datePicker.getValue() == null) {
            labelNoDate.setText("You have not selected any dates");
            labelNoDate.setVisible(true);
        } else {
            System.out.println("Qui appena spingo bottone dopo la data: " + bookingController.getChoosenCenter().getName());
            DailyAvailabilityBeanIn beanIn = new DailyAvailabilityBeanIn();
            beanIn.setDateToSearch(datePicker.getValue());

            DailyAvailabilityBeanOut beanOut;
            try {
                beanOut = bookingController.getDailyAvailability(beanIn);
            } catch (MyException exception) {
                labelNoDate.setText(exception.getMessage());
                labelNoDate.setVisible(true);
                return;
            }
            //beanOut.setDailyAvailability(availability); // non mi convince forse meglio passare alla gui successiva direttamente
            // il model FieldDailyAvailability!!!

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/daily_availability_weather.fxml"));
            Parent root = loader.load();
            AvailabilityControlGUI availabilityController = loader.getController();
            availabilityController.setBookingController(bookingController);
            availabilityController.setImages(beanOut);
            availabilityController.setAvailability(beanOut);

            availabilityController.setLabCenterName(bookingController.getChoosenCenter().getName());
            availabilityController.setLabDate(datePicker.getValue().toString());
            availabilityController.setLabPrice("€ " + bookingController.getChoosenCenter().getFieldPrice().toString());

            navigate.pushPage(((Node) e.getSource()).getScene());
            navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin() + 1);
            availabilityController.setNavigate(navigate);
            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            labelNoDate.setVisible(false);
            labelNoDate.setText("");
            window.setScene(new Scene(root));
        }
    }

    public void back(ActionEvent e) throws IOException {
        /*Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
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

    public void home (ActionEvent e) throws IOException {
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
