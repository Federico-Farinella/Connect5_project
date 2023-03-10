package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.controllers.BookingController;
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
        boolean isConfirmed = bookingController.confirmBooking(withReferee, withTunics);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/BookingResponse.fxml"));
        Parent root = loader.load();
        BookingResponseGUI controlGui = loader.getController();
        controlGui.setImageResponse(isConfirmed);
        controlGui.setLabResponse(isConfirmed);

        navigate.pushPage(((Node) e.getSource()).getScene());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()+1);
        controlGui.setNavigate(navigate);

        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
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
        window.setScene(navigate.getPages().lastElement());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()-1);
        navigate.pages.pop();
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

    //public
}
