package com.example.connect5_project.take_booking.view.gui;

import com.example.connect5_project.take_booking.controller.BookingController;
import com.example.connect5_project.exceptions.ConnectionDBException;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.exceptions.TakeBookingException;
import com.example.connect5_project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class SetBookingOptionalGUI {
    @FXML
    Label labCenterName;
    @FXML
    Label labDate;
    @FXML
    Label labHour;
    @FXML
    Label labPrice;
    @FXML
    CheckBox cBoxReferee;
    @FXML
    CheckBox cBoxTunics;

    private Navigate navigate;

    private BookingController bookingController;



    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    public void setLabCenterName(String centerName) {
        this.labCenterName.setText(centerName);
    }

    public void setLabDate(LocalDate date) {
        this.labDate.setText(date.toString());
    }

    public void setLabHour(String labHour) {
        this.labHour.setText(labHour);
    }

    public void setLabPrice(String labPrice) {
        this.labPrice.setText(labPrice);
    }

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void confirmBooking(ActionEvent e) throws IOException {
        boolean withReferee = false;
        boolean withTunics = false;
        if (cBoxReferee.isSelected())
            withReferee = true;
        if (cBoxTunics.isSelected())
            withTunics = true;
        boolean isConfirmed = false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BookingResponse.fxml"));
        Parent root = loader.load();
        BookingResponseGUI controlGui = loader.getController();
        try {
            isConfirmed = bookingController.confirmBooking(withReferee, withTunics);
            controlGui.setImageResponse(isConfirmed);
            controlGui.setLabResponse(isConfirmed);
        } catch (MyException exception0) {
            // Stampa messaggio dell eccezione!!!!
            controlGui.setImageResponse(false);
            controlGui.setLabResponse(false);
        } finally {

            navigate.pushPage(((Node) e.getSource()).getScene());
            navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin() + 1);
            controlGui.setNavigate(navigate);

            Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    public void back(ActionEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(navigate.getPages().peek());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()-1);
        navigate.getPages().pop();
    }

    public void home(ActionEvent e) throws Exception {
        Stage window;
        int currentPagesAfterLogin = navigate.getCountPagesAfterLogin();
        for (int i = 0; i < currentPagesAfterLogin-1 ; i++) {
            navigate.getPages().pop();
        }
        navigate.setCountPagesAfterLogin(0);
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(navigate.getPages().peek());
        navigate.getPages().pop();
    }

}
