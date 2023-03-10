package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.history.History;
import com.example.connect5_project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoggedGUI {
     private Navigate navigate;
    @FXML
     private Label labelWelcome;

    public Navigate getNavigate() {
        return navigate;
    }

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public Label getLabelWelcome() {
        return labelWelcome;
    }

    /*public void back(ActionEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println(window);
        window.setScene(navigate.pages.lastElement());
        navigate.pages.pop();
        //window.setScene(History.pagine.lastElement());
        //History.pagine.pop();
    }*/

    public void home(ActionEvent e) throws Exception {
        History.pagine.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        //Parent root = FXMLLoader.load(getClass().getResource("/Connect5.fxml"));
        Stage window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void searchSportCenters(ActionEvent e) throws IOException {
        //History.pagine.add(((Node) e.getSource()).getScene());
        navigate.pushPage(((Node) e.getSource()).getScene());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin() + 1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SearchSportCenters.fxml"));
        Parent root = loader.load();
        SearchSportCentersGUI control = loader.getController();
        control.setNavigate(navigate);
        Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
