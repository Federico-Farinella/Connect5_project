package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.bean.CreateAccountBean;
import com.example.connect5_project.history.Navigate;
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

public class CreateAccountGUI {  // Devo sostituire il ritorno di confirmCreate con un bean mettendo dentro un response
    @FXML
    TextField insertName;
    @FXML
    TextField insertSurname;
    @FXML
    TextField insertEmail;
    @FXML
    TextField insertPassword;
    @FXML
    Button btnConfirmCreate;
    @FXML
    Label errorLabel;

    private Navigate navigate;

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void back(MouseEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        window.setScene(navigate.getPages().lastElement());
        navigate.getPages().pop();
    }

    public void home(MouseEvent e) throws Exception {
        Stage window;
        navigate.getPages().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = loader.load();
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void confirmCreate(){
        CreateAccountBean bean = new CreateAccountBean(insertName.getText(), insertSurname.getText(), insertEmail.getText(), insertPassword.getText());
        boolean validEmail = bean.isValidEmail(bean.getEmail());
        if (!validEmail) {
            errorLabel.setText("Invalid email.  Please insert a valid email");
            errorLabel.setVisible(true);
            return;
        }
        else {
            System.out.println("Valid email. OK");
        }

        boolean validPass = bean.isValidPassword(bean.getPassword());
        if (!validPass) {
            errorLabel.setText("Password contains less than 6 characters");
            errorLabel.setVisible(true);
        }
        else {
            System.out.println("Valid Password. OK");
        }
    }
}
