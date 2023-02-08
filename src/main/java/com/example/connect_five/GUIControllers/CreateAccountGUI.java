package com.example.connect_five.GUIControllers;

import com.example.connect_five.bean.CreateAccountBean;
import com.example.connect_five.history.History;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateAccountGUI {
    @FXML
    TextField insertName, insertSurname, insertEmail, insertPassword;
    @FXML
    Button btnConfirmCreate;
    @FXML
    Label errorLabel;

    public void back(MouseEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println(window);
        window.setScene(History.pagine.lastElement());
        History.pagine.pop();
    }

    public void home(MouseEvent e) throws Exception {
        Stage window;
        History.pagine.clear();
        Parent root = FXMLLoader.load(getClass().getResource("/Connect5.fxml"));
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void confirmCreate(){
        CreateAccountBean bean = new CreateAccountBean(insertName.getText(), insertSurname.getText(), insertEmail.getText(), insertPassword.getText());
        Boolean validEmail = bean.isValidEmail(bean.getEmail());
        if (validEmail == false) {
            errorLabel.setText("Invalid email.  Please insert a valid email");
            errorLabel.setVisible(true);
            return;
        }
        else {
            System.out.println("Valid email. OK");
        }

        Boolean validPass = bean.isValidPassword(bean.getPassword());
        if (validPass == false) {
            errorLabel.setText("Password contains less than 6 characters");
            errorLabel.setVisible(true);
        }
        else {
            System.out.println("Valid Password. OK");
        }
    }
}
