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

    public void home(ActionEvent e) throws Exception {
        History.pagine.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        Stage window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void searchSportCenters(ActionEvent e) throws IOException {
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
