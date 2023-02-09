package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.history.History;
import com.example.connect5_project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FirstScreenGUI {
    Navigate navigate;
    @FXML
    Button btnLog;
    @FXML
    Button btnCreatAcc;

    public void login(ActionEvent e) throws Exception{
        Stage window;

        navigate.pushPage(((Node) e.getSource()).getScene());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
        Parent root = loader.load();
        LoginGUI contr = loader.getController();
        contr.setNavigate(navigate);

        window =(Stage) btnLog.getScene().getWindow();
        System.out.println(btnLog);
        window.setScene(new Scene(root));
        System.out.println("you clicked me");

    }
    public void createAccount(ActionEvent e) throws Exception {
        Stage window;
        History.pagine.add(((Node) e.getSource()).getScene());
        Parent root = FXMLLoader.load(getClass().getResource("/CreateAccount.fxml"));
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void centersOwner(ActionEvent e) throws Exception {
        Stage window;
        History.pagine.add(((Node) e.getSource()).getScene());
        Parent root = FXMLLoader.load(getClass().getResource("/CentersOwner.fxml"));
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }
    public void initialize() {
        navigate = new Navigate();
    }

}
