package com.example.connect5_project.take_booking.view.gui;

import com.example.connect5_project.take_booking.bean.DailyAvailabilityBeanRequest;
import com.example.connect5_project.take_booking.bean.DailyAvailabilityBeanResponse;
import com.example.connect5_project.take_booking.controller.BookingController;
import com.example.connect5_project.exceptions.MyException;
import com.example.connect5_project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

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

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    public void search(ActionEvent e) throws IOException {
        if (datePicker.getValue() == null) {
            labelNoDate.setText("You have not selected any dates");
            labelNoDate.setVisible(true);
        } else {
            System.out.println("Qui appena spingo bottone dopo la data: " + bookingController.getChoosenCenter().getName());
            DailyAvailabilityBeanRequest beanIn = new DailyAvailabilityBeanRequest();
            beanIn.setDateToSearch(datePicker.getValue());

            DailyAvailabilityBeanResponse beanOut;
            try {
                beanOut = bookingController.getDailyAvailability(beanIn);
            } catch (MyException exception) {
                labelNoDate.setText(exception.getMessage());
                labelNoDate.setVisible(true);
                return;
            }

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

    public void setDatesToChoose(LocalDate minDate, LocalDate maxDate) {
        this.getDatePicker().setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(maxDate) || item.isBefore(minDate));
                    }});

    }

    public void back(ActionEvent e) throws IOException {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        System.out.println(window);
        window.setScene(navigate.getPages().peek());
        navigate.getPages().pop();
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()-1);
    }

    public void home (ActionEvent e) throws IOException {
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
