package com.example.connect5_project.gui_cntrollers;


import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.history.Navigate;
import com.example.connect5_project.models.CentroSportivo;
import com.example.connect5_project.utility.BusinessConstants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public class SportCentersResultsGUI {
    private Navigate navigate;
    private List<CentroSportivo> list;
    @FXML
    VBox box;
    @FXML
    Button btn1;
    @FXML
    GridPane grid;

    //ArrayList<GridPane> list;

    private BookingController bookingController;
    //static int i = 0;


    public List<CentroSportivo> getList() {
        return list;
    }

    public void setList(List<CentroSportivo> list) {
        this.list = list;
    }

    public BookingController getBookingController() {
        return bookingController;
    }

    public void setBookingController(BookingController booking_controller) {
        this.bookingController = booking_controller;
        System.out.println("Setting Booking Controller: " + this.bookingController);
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

        System.out.println(window);
        //navigate.pages.pop();
        window.setScene(navigate.getPages().lastElement());
        navigate.pages.pop();
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()-1);
        //History.pagine.pop();
    }

    public void home(MouseEvent e) throws Exception {
        /*Stage window;
        //History.pagine.clear();
        navigate.pages.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
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

    public void chooseCenter(ActionEvent e) throws Exception {
        System.out.println("Booking_controller step 2: " + bookingController);
        Node source = (Node) e.getSource();
        GridPane gridPane = (GridPane) source.getParent().getParent();
        System.out.println(gridPane);
        Label name = (Label) gridPane.lookup("#Name");
        System.out.println(name.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChooseBookingData.fxml"));
        Parent root = loader.load();
        ChooseBookingDataGUI choose_data_controller = loader.getController();
        bookingController.setChoosenCenter(name.getText());

        /////////
        System.out.println("Qui di ritorno da setChoosenCenter: " + bookingController.getChoosenCenter().getName());
        System.out.println("City of choosen center: " + bookingController.getChoosenCenter().getCity());
        choose_data_controller.setBookingController(bookingController);
        DatePicker datePicker = choose_data_controller.getDatePicker();
        LocalDate min_date = LocalDate.now();
        LocalDate max_date = LocalDate.now().plusDays(BusinessConstants.DAY_TO_CHOOSE);
        datePicker.setDayCellFactory(d ->
                new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        setDisable(item.isAfter(max_date) || item.isBefore(min_date));
                    }});
        navigate.pushPage(((Node) e.getSource()).getScene());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()+1);
        choose_data_controller.setNavigate(navigate);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));

        //String name = node.getParent().
    }


    /*public void add(){
        SportCenterElement el = new SportCenterElement();
        list = new ArrayList<GridPane>();
        list.add(el.returnGridPane());
        System.out.println(list.get(i));
        box.getChildren().add(list.get(i));

    }*/

}
