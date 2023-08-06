package com.example.connect5_project.gui_cntrollers;

import com.example.connect5_project.LoginController;
import com.example.connect5_project.bean.LoginBeanIn;
import com.example.connect5_project.bean.LoginBeanOut;
import com.example.connect5_project.history.Navigate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginGUI {
    private Navigate navigate;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnHome;
    @FXML
    Button btnConfirm;
    @FXML
    TextField insertEmailLog;
    /*@FXML
    TextField insertPassLog;*/
    @FXML
    PasswordField insertPassLog;
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
        //History.pagine.clear();
        navigate.getPages().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }


    public void loginVerify(ActionEvent e) throws Exception {
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
        LoginController loginController = new LoginController();
        LoginBeanOut ret = loginController.loginVerify(beanIn);

        if (!ret.isSuccess()) {
            System.out.println("Sono nell if prima dello switch");
            System.out.println(ret.getResponse());
            switch (ret.getResponse()) {
                case ("Error") -> {
                    System.out.println("Sono nel case Error");
                    errorLabel.setText("Error, we're working on fixing the issues, come back later");
                }
                case ("Email not registered") -> {
                    System.out.println("Sono nel case Email not registered");
                    errorLabel.setText("Email not registered. Please insert a registered email");
                }
                case ("Password incorrect") -> {
                    System.out.println("Sono nel case Password incorrect");
                    errorLabel.setText("Password incorrect");
                }
            }
        } else {
            errorLabel.setText("");
            errorLabel.setVisible(false);
            insertEmailLog.setText("");
            insertPassLog.setText("");
            Stage window;
            //Navigate navigate = new Navigate();
            navigate.pushPage(((Node) e.getSource()).getScene());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Logged.fxml"));
            Parent root = loader.load();
            LoggedGUI controlGui = loader.getController();
            this.navigate.setCountPagesAfterLogin(0);
            controlGui.setNavigate(navigate);
            controlGui.getLabelWelcome().setText("Welcome\n" + ret.getUser().getNickName());
            window = (Stage) ((Node) e.getSource()).getScene().getWindow();
            window.setScene(new Scene(root));
        }

        errorLabel.setVisible(true);
        System.out.println(ret);
    }
}
