package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.bean.SearchResultBeanOut;
import com.example.connect5_project.bean.SearchResultsBeanIn;
import com.example.connect5_project.controllers.BookingController;
import com.example.connect5_project.controllers.SearchSportCenters;
import com.example.connect5_project.dao.SportCenterDAO;
import com.example.connect5_project.history.Navigate;
import com.example.connect5_project.utility.SportCenterElements;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SearchSportCentersGUI {
    private Navigate navigate;
    private BookingController booking_controller;
    @FXML private TextField nomeCentroS;
    @FXML private TextField cittaCentroS;
    @FXML private TextField viaCentroS;
    @FXML
    private Button btnSearch;
    @FXML
    private Label errorLab;

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void back(ActionEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println(window);
        window.setScene(navigate.getPages().lastElement());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()-1);
        navigate.pages.pop();
        //window.setScene(History.pagine.lastElement());
        //History.pagine.pop();
    }

    public void home(ActionEvent e) throws Exception {
        Stage window;
        int currentPagesAfterLogin = navigate.getCountPagesAfterLogin();
        for (int i = 0; i < currentPagesAfterLogin-1 ; i++) {
            navigate.getPages().pop();
        }
        navigate.setCountPagesAfterLogin(0);
        //navigate.getPages().clear();
        //History.pagine.clear();
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        //Parent root = loader.load();     // tolta avendo introdotto variabile countPagesAfterLogin
        //Parent root = FXMLLoader.load(getClass().getResource("/Connect5.fxml"));  // Anche questa
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(navigate.getPages().lastElement());
        navigate.getPages().pop();
        //window.setScene(new Scene(root)); // Anche questa

    }

    public void search(ActionEvent e) throws Exception {
        String name = nomeCentroS.getText();
        String city = cittaCentroS.getText();
        //String via = viaCentroS.getText();
        SearchSportCenters ctrl = new SearchSportCenters();
        System.out.println("SearchSportCentersGui qui sono:-1");
        SportCenterDAO cDao = new SportCenterDAO();
        SearchResultBeanOut list;
        booking_controller = new BookingController();
        SearchResultsBeanIn bean_in = new SearchResultsBeanIn();
        if (name.equals("") && city.equals("")) {
            errorLab.setText("All fields empty");
            errorLab.setVisible(true);
            //list = new SearchResultBeanOut();
            //list.setDaoResponse("Both fields empty");
            return;
            //return list;
        } else if (!name.equals("") && city.equals("")) {
            bean_in.setSearchMode("Name");
            bean_in.setName(name);
            nomeCentroS.setText("");
        } else if (name.equals("")) {
            bean_in.setSearchMode("City");
            bean_in.setCity(city);
            cittaCentroS.setText("");
            //list = cDao.dbSearchCentersByCity(city);
        } else {
            bean_in.setSearchMode("Name and city");
            bean_in.setName(name);
            bean_in.setCity(city);
            nomeCentroS.setText("");
            cittaCentroS.setText("");
        }
        list = booking_controller.searchCenters(bean_in);
        //list = booking_controller.
        //list = cDao.dbSearchCentersByCity(city);
        //SearchResultBean list = ctrl.findByCity(city);
        //System.out.println(list.getListOfCenters().get(0).getName());
        //System.out.println("SearchSportCentersGui qui sono:0");
        //SportCenterListElements.setList(list.getListOfCenters());

        //History.pagine.add(((Node) e.getSource()).getScene());
        errorLab.setVisible(false);
        navigate.pushPage(((Node) e.getSource()).getScene());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin() + 1); // Aggiunto avendo introdotto countPagesAfterLogin
        System.out.println("SearchSportCentersGui qui sono:1");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SportCentersResults.fxml"));
        System.out.println("SearchSportCentersGui qui sono:2");
        Parent root = loader.load();
        SportCentersResultsGUI controlGui = loader.getController();
        controlGui.setBookingController(this.booking_controller);
        controlGui.setList(list.getListOfCenters());
        controlGui.setNavigate(navigate);
        if (list.getListOfCenters() == null) {
            System.out.println("Ora creo la label");
            Label lab = new Label("No results found for your query");
            lab.setPrefWidth(180.0);
            lab.setPrefHeight(70.0);
            lab.setAlignment(Pos.CENTER);
            lab.setTextAlignment(TextAlignment.CENTER);
            //lab.setTextFill(Paint.valueOf("white"));
            lab.setTextFill(Paint.valueOf( "rgb(179,252,252)"));
            lab.setFont(Font.font("Stalin One", 13));
            lab.setWrapText(true);
            controlGui.getBox().getChildren().add(lab);

        } else {
            SportCenterElements centerElement = new SportCenterElements(list.getListOfCenters(), controlGui);
            ArrayList<GridPane> array = centerElement.getPanels();
            for (GridPane item : array) {
                controlGui.getBox().getChildren().add(item);
            }
        }
        //booking_controller.setCentersResultsList(list.getListOfCenters());
        System.out.println("Booking_controller: " + booking_controller);
        //controlGui.setBooking_controller(booking_controller);
        //Parent root = FXMLLoader.load(getClass().getResource("/SportCentersResults.fxml"));
        Stage window =(Stage) btnSearch.getScene().getWindow();
        System.out.println("SearchSportCentersGui qui sono:3");
        window.setScene(new Scene(root));
        System.out.println("SearchSportCentersGui qui sono:4");
        //History.pagine.add(((Node) e.getSource()).getScene());
        //Parent root = FXMLLoader.load(getClass().getResource("/"));
        // Prove: nomeCentroS.setText(list.get(0).getName());
        //viaCentroS.setText(list.get(1).getName());

    }

    //@FXML private VBox vBox;
/*
    public void search(ActionEvent e) {
        CercaCentriSControl control = new CercaCentriSControl();
        String nome = nomeCentroS.getText();
        String citta = cittaCentroS.getText();
        String via = viaCentroS.getText();
        ArrayList<String> results = new ArrayList<>();
        results = control.searchCentri(nome, citta, via);
        Stage window = (Stage)((Node) e.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/SportCentersSearchResults.fxml"));
        window.setScene((new Scene(root)));
        vBox.getChildren().add()...

    }*/

}
