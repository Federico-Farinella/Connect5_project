package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.bean.DailyAvailabilityBeanIn;
import com.example.connect5_project.bean.DailyAvailabilityBeanOut;
import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.history.Navigate;
import com.example.connect5_project.models.FieldDailyAvailability;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseBookingDataGUI {

    private BookingController bookingController;
    private Navigate navigate;

    @FXML
    private DatePicker datePicker;

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

    public void search(ActionEvent e) throws Exception {
        System.out.println("Qui appena spingo bottone dopo la data: " + bookingController.getChoosenCenter().getName());
        DailyAvailabilityBeanIn bean_in = new DailyAvailabilityBeanIn();
        bean_in.setDateToSearch(datePicker.getValue());
        DailyAvailabilityBeanOut bean_out = bookingController.getDailyWeather(bean_in);

        FieldDailyAvailability availability = bookingController.getDailyAvailability(bean_in);
        bean_out.setDailyAvailability(availability);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/daily_availability_weather.fxml"));
        Parent root = loader.load();
        AvailabilityControlGUI availabilityController = loader.getController();
        availabilityController.setBookingController(bookingController);
        availabilityController.setImages(bean_out);
        availabilityController.setAvailability(bean_out);

        availabilityController.setLabCenterName(bookingController.getChoosenCenter().getName());
        availabilityController.setLabDate(datePicker.getValue().toString());

        navigate.pushPage(((Node) e.getSource()).getScene());
        availabilityController.setNavigate(navigate);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void back(ActionEvent e) throws IOException {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(navigate.getPages().lastElement());
        navigate.pages.pop();
    }

    public void home (ActionEvent e) throws IOException {
        navigate.pages.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        Stage window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
