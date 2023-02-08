package com.example.connect_five.GUIControllers;


import com.example.connect_five.history.Navigate;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SportCentersResultsGUI {
    private Navigate navigate;

    @FXML
    VBox box;

    @FXML
    Button btn1;

    @FXML
    GridPane grid;

    ArrayList<GridPane> list;

    static int i = 0;

    public VBox getBox() {
        return box;
    }

    public Navigate getNavigate() {
        return navigate;
    }

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void back(MouseEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();

        System.out.println(window);
        navigate.pages.pop();
        window.setScene(navigate.getPages().lastElement());
        navigate.pages.pop();
        //History.pagine.pop();
    }

    public void home(MouseEvent e) throws Exception {
        Stage window;
        //History.pagine.clear();
        navigate.pages.clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Connect5.fxml"));
        Parent root = loader.load();
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(new Scene(root));
    }

    /*public void add(){
        SportCenterElement el = new SportCenterElement();
        list = new ArrayList<GridPane>();
        list.add(el.returnGridPane());
        System.out.println(list.get(i));
        box.getChildren().add(list.get(i));

    }*/

}
