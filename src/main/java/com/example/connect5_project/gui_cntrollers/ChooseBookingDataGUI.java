package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.history.Navigate;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

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

    public void search() {

    }

    public void back() {

    }

    public void home () {

    }
}
