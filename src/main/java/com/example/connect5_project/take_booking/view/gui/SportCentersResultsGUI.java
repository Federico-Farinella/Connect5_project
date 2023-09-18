package com.example.connect5_project.take_booking.view.gui;


import com.example.connect5_project.take_booking.controller.BookingController;
import com.example.connect5_project.history.Navigate;
import com.example.connect5_project.take_booking.model.SportCenter;
import com.example.connect5_project.utility.BusinessConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.List;

public class SportCentersResultsGUI {
    private Navigate navigate;
    private List<SportCenter> list;
    @FXML
    VBox box;
    @FXML
    Button btn1;
    @FXML
    GridPane grid;


    private BookingController bookingController;


    public List<SportCenter> getList() {
        return list;
    }

    public void setList(List<SportCenter> list) {
        this.list = list;
    }

    public BookingController getBookingController() {
        return bookingController;
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    public VBox getBox() {
        return box;
    }

    public Navigate getNavigate() {
        return navigate;
    }

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void back(MouseEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(navigate.getPages().peek());
        navigate.getPages().pop();
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()-1);
    }

    public void home(MouseEvent e) throws Exception {
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

    public void chooseCenter(ActionEvent e) throws Exception {
        Node source = (Node) e.getSource();
        GridPane gridPane = (GridPane) source.getParent().getParent();
        Label name = (Label) gridPane.lookup("#Name");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChooseBookingData.fxml"));
        Parent root = loader.load();
        ChooseBookingDataGUI chooseDataControllerGUI = loader.getController();
        bookingController.setChoosenCenter(name.getText());

        /////////
        chooseDataControllerGUI.setBookingController(bookingController);
        LocalDate minDate = LocalDate.now();
        LocalDate maxDate = LocalDate.now().plusDays(BusinessConstants.getDayToChoose());
        chooseDataControllerGUI.setDatesToChoose(minDate, maxDate);

        navigate.pushPage(((Node) e.getSource()).getScene());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()+1);
        chooseDataControllerGUI.setNavigate(navigate);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));

    }

}
