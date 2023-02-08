package com.example.connect5_project.gui_cntrollers;


import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.history.Navigate;
import com.example.connect5_project.models.CentroSportivo;
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

import java.util.ArrayList;

public class SportCentersResultsGUI {
    private Navigate navigate;
    private ArrayList<CentroSportivo> list;
    @FXML
    VBox box;
    @FXML
    Button btn1;
    @FXML
    GridPane grid;

    //ArrayList<GridPane> list;

    private BookingController booking_controller;
    //static int i = 0;


    public ArrayList<CentroSportivo> getList() {
        return list;
    }

    public void setList(ArrayList<CentroSportivo> list) {
        this.list = list;
    }

    public BookingController getBooking_controller() {
        return booking_controller;
    }

    public void setBooking_controller(BookingController booking_controller) {
        this.booking_controller = booking_controller;
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
        navigate.pages.pop();
        window.setScene(navigate.getPages().lastElement());
        navigate.pages.pop();
        //History.pagine.pop();
    }

    public void home(MouseEvent e) throws Exception {
        Stage window;
        //History.pagine.clear();
        navigate.pages.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void chooseCenter(ActionEvent e) throws Exception {
        Node source = (Node) e.getSource();
        GridPane gridPane = (GridPane) source.getParent().getParent();
        System.out.println(gridPane);
        Label name = (Label) gridPane.lookup("#Name");
        System.out.println(name.getText());
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
