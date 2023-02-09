package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.bean.DailyAvailabilityBeanIn;
import com.example.connect5_project.bean.DailyAvailabilityBeanOut;
import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.time.LocalDate;

public class ChooseBookingDataGUI {

    private BookingController booking_controller;
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
        this.booking_controller = booking_controller;
    }

    public void search(ActionEvent e) throws Exception {
        System.out.println("Qui appena spingo bottone dopo la data: " + booking_controller.getChoosenCenter().getName());
        DailyAvailabilityBeanIn bean_in = new DailyAvailabilityBeanIn();
        bean_in.setDateToSearch(datePicker.getValue());
        DailyAvailabilityBeanOut bean_out = booking_controller.getAvailability(bean_in);
        System.out.println("Prova meteo: " + bean_out.getWeatherByHour().get(0));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/daily_availability_weather.fxml"));
        Parent root = loader.load();
        AvailabilityControlGUI availability_controller = loader.getController();
        availability_controller.setBooking_controller(booking_controller);
        availability_controller.setImages(bean_out.getWeatherByHour());
        availability_controller.setLabCenterName(booking_controller.getChoosenCenter().getName());
        availability_controller.setLabDate(datePicker.getValue().toString());

        navigate.pushPage(((Node) e.getSource()).getScene());
        availability_controller.setNavigate(navigate);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
        //LocalDate date_to_search = datePicker.getValue();
        //bean_out = booking_controller.getAvailability(bean_in);

    }

    public void back(ActionEvent e) throws IOException {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        System.out.println(window);
        //navigate.pages.pop();
        window.setScene(navigate.getPages().lastElement());
        navigate.pages.pop();
    }

    public void home (ActionEvent e) throws IOException {
        Stage window;
        //History.pagine.clear();
        navigate.pages.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
