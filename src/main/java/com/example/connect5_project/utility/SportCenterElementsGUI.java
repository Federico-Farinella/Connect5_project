package com.example.connect5_project.utility;

import com.example.connect5_project.take_booking.view.gui.SportCentersResultsGUI;
import com.example.connect5_project.take_booking.model.SportCenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.ArrayList;
import java.util.List;

public class SportCenterElementsGUI {
    private static final String ELEMENT_GRAPHIC = "-fx-background-color :  rgb(0,162,11); -fx-background-radius:10";
    private static final String BUTTON_GRAPHIC = "-fx-background-color: black; -fx-background-radius:10";
    private List<GridPane> panels;

    public List<GridPane> getPanels() {
        return panels;
    }

    public SportCenterElementsGUI(List<SportCenter> list, SportCentersResultsGUI contr) {
        panels = new ArrayList<>();
        GridPane pane;
        for (SportCenter item : list) {
            pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPrefSize(310, 102);
            pane.setStyle(ELEMENT_GRAPHIC);
            Label lab = new Label();
            lab.setPrefWidth(180.0);
            lab.setPrefHeight(70.0);
            lab.setAlignment(Pos.CENTER);
            lab.setTextAlignment(TextAlignment.CENTER);
            lab.setText(item.getName());
            lab.setTextFill(Paint.valueOf("black"));
            lab.setFont(Font.font("Stalin One", 10));
            lab.setId("Name");
            lab.setWrapText(true);
            pane.add(lab, 1, 0);
            ImageView im = new ImageView();
            im.setFitHeight(54);
            im.setFitWidth(78);
            im.setImage(new Image(String.valueOf(getClass().getResource("/images/" + item.getImage()))));
            pane.add(im, 0, 0, 1, 2);
            HBox box = new HBox();
            box.setPrefSize(180, 100);
            box.setAlignment(Pos.CENTER);
            box.setSpacing(40);

            Button go = new Button("Go");
            go.setPrefWidth(70);
            go.setFont(Font.font("Sarpanch", 13));
            go.setStyle(BUTTON_GRAPHIC);
            go.setTextFill(Paint.valueOf("white"));
            go.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        contr.chooseCenter(event);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            box.getChildren().add(go);

            pane.add(box, 1, 1);
            panels.add(pane);
        }
    }

    public SportCenterElementsGUI( ) {
        GridPane pane;
        pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPrefSize(310, 102);
        pane.setStyle("-fx-background-color :  rgb(179,252,252);" + "-fx-background-radius:10");
        Label lab = new Label();
        lab.setPrefWidth(180.0);
        lab.setPrefHeight(70.0);
        lab.setAlignment(Pos.CENTER);
        lab.setTextAlignment(TextAlignment.CENTER);
        lab.setText("Tor Pagnotta Sports");
        lab.setTextFill(Paint.valueOf("black"));
        lab.setFont(Font.font("Stalin One", 10 ));
        lab.setWrapText(true);
        pane.add(lab, 1, 0);
        ImageView im = new ImageView();
        im.setFitHeight(54);
        im.setFitWidth(78);
        im.setImage(new Image(String.valueOf(getClass().getResource("/images/"+"sfondo.jpg"))));
        pane.add(im, 0,0,1,2);
        HBox box = new HBox();
        box.setPrefSize(180, 100);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(40);

        Button go = new Button("Go");
        go.setPrefWidth(70);
        go.setFont(Font.font("Sarpanch", 13));
        go.setStyle("-fx-background-color: black;"+"-fx-background-radius:10");
        go.setTextFill(Paint.valueOf("white"));
        box.getChildren().add(go);

        pane.add(box,1,1);
    }
}
