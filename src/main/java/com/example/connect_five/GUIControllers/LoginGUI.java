package com.example.connect_five.GUIControllers;

import com.example.connect_five.Login;
import com.example.connect_five.bean.LoginBeanIn;
import com.example.connect_five.bean.LoginBeanOut;
import com.example.connect_five.history.History;
import com.example.connect_five.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginGUI {
    @FXML
    Button btnBack;
    @FXML
    Button btnHome;
    private Navigate navigate;
    @FXML
    Button btnConfirm;
    @FXML
    TextField insertEmailLog;
    @FXML
    TextField insertPassLog;
    @FXML
    Label errorLabel;


    public Navigate getNavigate() {
        return navigate;
    }

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void back(ActionEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //System.out.println(window);
        //window.setScene(History.pagine.lastElement());
        window.setScene(navigate.getPages().lastElement());
        //LoginGUI controller = (LoginGUI) ((Node) e.getSource()).getScene().getWindow().getUserData();
        navigate.getPages().pop();
        //History.pagine.pop();
    }

    public void home(ActionEvent e) throws Exception {
        Stage window;
        History.pagine.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }


    public void loginVerify(ActionEvent e) throws Exception {
        //System.out.println(btnLog);
        errorLabel.setVisible(false);
        System.out.println(insertEmailLog);
        String email = insertEmailLog.getText();
        String password = insertPassLog.getText();
        System.out.println(email);
        System.out.println(password);

        if (email.equals("") || password.equals("")) {
            errorLabel.setText("Fields cannot be empty");
            errorLabel.setVisible(true);
            return;
        }

        LoginBeanIn beanIn = new LoginBeanIn();
        if (!beanIn.setEmail(email)) {
            errorLabel.setText("Invalid email.  Please insert a valid email");
            errorLabel.setVisible(true);
            return;
        }



        beanIn.setPassword(password);
        //System.out.println(email);
        //Login login = new Login();
        Login login = new Login();
        LoginBeanOut ret = login.loginVerify(beanIn);

        switch (ret.getResponse()) {
            case ("Error") -> {
                errorLabel.setText("Error, we're working on fixing the issues, come back later");
            }
            case ("Email not registered") -> {
                errorLabel.setText("Email not registered. Please insert a registered email");
            }
            case ("Password incorrect") -> {
                errorLabel.setText("Password incorrect");
            }
            case ("Match") -> {
                errorLabel.setText("");
                errorLabel.setVisible(false);
                insertEmailLog.setText("");
                insertPassLog.setText("");
                Stage window;
                Navigate navigate = new Navigate();
                navigate.pushPage(((Node) e.getSource()).getScene());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Logged.fxml"));
                Parent root = loader.load();
                LoggedGUI controlGui = loader.getController();
                controlGui.setNavigate(navigate);
                controlGui.getLabelWelcome().setText("Welcome\n" + ret.getUser().getNickName());
                window = (Stage) ((Node)e.getSource()).getScene().getWindow();
                window.setScene(new Scene(root));
            }
        }
        if (ret.getResponse().equals("Error")) {
            errorLabel.setText("Error, we're working on fixing the issues, come back later");
        }

        errorLabel.setVisible(true);
        System.out.println(ret);
    }
}
