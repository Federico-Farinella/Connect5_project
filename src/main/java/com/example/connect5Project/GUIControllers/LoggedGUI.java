package com.example.connect5Project.GUIControllers;

import com.example.connect5Project.history.History;
import com.example.connect5Project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoggedGUI {
    Navigate navigate;
    @FXML
    Label labelWelcome;

    public Navigate getNavigate() {
        return navigate;
    }

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public Label getLabelWelcome() {
        return labelWelcome;
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
        History.pagine.clear();
        Parent root = FXMLLoader.load(getClass().getResource("/Connect5.fxml"));
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void searchSportCenters(ActionEvent e) throws Exception {
        Stage window;
        //History.pagine.add(((Node) e.getSource()).getScene());
        navigate.pages.add(((Node) e.getSource()).getScene());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchSportCenters.fxml"));
        Parent root = loader.load();
        SearchSportCentersGUI control = loader.getController();
        control.setNavigate(navigate);
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
