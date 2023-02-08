package com.example.connect5Project.GUIControllers;

import com.example.connect5Project.bean.SearchResultBean;
import com.example.connect5Project.controllers.SearchSportCenters;
import com.example.connect5Project.history.Navigate;
import com.example.connect5Project.utility.SportCenterElement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SearchSportCentersGUI {
    private Navigate navigate;
    @FXML private TextField nomeCentroS;
    @FXML private TextField cittaCentroS;
    @FXML private TextField viaCentroS;

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void back(MouseEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println(window);
        window.setScene(navigate.pages.lastElement());
        navigate.pages.pop();
        //window.setScene(History.pagine.lastElement());
        //History.pagine.pop();
    }

    public void home(MouseEvent e) throws Exception {
        Stage window;
        navigate.pages.clear();
        //History.pagine.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("/Connect5.fxml"));
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    //@FXML private ListView list;
    @FXML
    private Button btnSearch;



    public void search(ActionEvent e) throws Exception {
        String name = nomeCentroS.getText();
        String city = cittaCentroS.getText();
        //String via = viaCentroS.getText();
        SearchSportCenters ctrl = new SearchSportCenters();
        System.out.println("SearchSportCentersGui qui sono:-1");
        SearchResultBean list = ctrl.findByCity(city);
        System.out.println(list.getListOfCenters().get(0).getName());
        System.out.println("SearchSportCentersGui qui sono:0");
        //SportCenterListElements.setList(list.getListOfCenters());

        //History.pagine.add(((Node) e.getSource()).getScene());
        navigate.pages.add(((Node) e.getSource()).getScene());
        System.out.println("SearchSportCentersGui qui sono:1");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SportCentersResults.fxml"));
        System.out.println("SearchSportCentersGui qui sono:2");
        Parent root = loader.load();
        SportCentersResultsGUI control = loader.getController();
        control.setNavigate(navigate);
        SportCenterElement centerElement = new SportCenterElement(list.getListOfCenters());
        ArrayList<GridPane> array = centerElement.getPanels();
        for (GridPane item : array) {
            control.getBox().getChildren().add(item);
        }
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
