package com.example.connect_5.utility;

import com.example.connect_5.models.CentroSportivo;
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

public class SportCenterElement {
    //Continuerò da qui dopo aver fatto pratica nel progetto "TabellaRisultati"
    ArrayList<GridPane> panels;
    //GridPane pane;

    public ArrayList<GridPane> getPanels() {
        return panels;
    }

    public SportCenterElement(ArrayList<CentroSportivo> list) {
        panels = new ArrayList<>();
        GridPane pane;
        //int i=0;
        for (CentroSportivo item : list) {
            //panels.add(new GridPane());
            //pane = panels.get(i);
            pane = new GridPane();
            pane.setAlignment(Pos.CENTER);
            pane.setPrefSize(310, 102);
            pane.setStyle("-fx-background-color :  rgb(179,252,252);" + "-fx-background-radius:10");
            Label lab = new Label();
            lab.setPrefWidth(180.0);
            lab.setPrefHeight(70.0);
            lab.setAlignment(Pos.CENTER);
            lab.setTextAlignment(TextAlignment.CENTER);
            //lab.setText("Tor Pagnotta Sports");
            lab.setText(item.getName());
            lab.setTextFill(Paint.valueOf("black"));
            lab.setFont(Font.font("Stalin One", 10));
            lab.setWrapText(true);
            //lab.setStyle("-fx-background-color: white");
            //pane.setGridLinesVisible(true);
            pane.add(lab, 1, 0);
            ImageView im = new ImageView();
            im.setFitHeight(54);
            im.setFitWidth(78);
            //im.setImage(new Image(String.valueOf(getClass().getResource("/images/" + "sfondo.jpg"))));
            im.setImage(new Image(String.valueOf(getClass().getResource("/images/" + item.getImage()))));
            //im.setStyle("-fx-border-color: white");
            pane.add(im, 0, 0, 1, 2);
            HBox box = new HBox();
            box.setPrefSize(180, 100);
            box.setAlignment(Pos.CENTER);
            box.setSpacing(40);

            Button go = new Button("Go");
            go.setPrefWidth(70);
            go.setFont(Font.font("Sarpanch", 13));
            go.setStyle("-fx-background-color: black;" + "-fx-background-radius:10");
            go.setTextFill(Paint.valueOf("white"));
            box.getChildren().add(go);

        /*Button info = new Button("Info");
        info.setFont(Font.font("Sarpanch", 13));
        info.setStyle("-fx-background-color: black");
        info.setTextFill(Paint.valueOf("white"));*/

        /*Button map = new Button("Map");
        map.setFont(Font.font("Sarpanch", 13));
        map.setStyle("-fx-background-color: black");
        map.setTextFill(Paint.valueOf("white"));
        box.getChildren().addAll(info, map);*/
            //box.setStyle("-fx-background-color: white");

            pane.add(box, 1, 1);

            //Label lb2 = new Label();
            //lb2.setText("BellaZi");
            //pane.add(lb2, 0,1);
            int col = pane.getColumnCount();
            System.out.println(col);
            panels.add(pane);
        }
    }

    public SportCenterElement( ) {
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
        //lab.setStyle("-fx-background-color: white");
        //pane.setGridLinesVisible(true);
        pane.add(lab, 1, 0);
        ImageView im = new ImageView();
        im.setFitHeight(54);
        im.setFitWidth(78);
        im.setImage(new Image(String.valueOf(getClass().getResource("/images/"+"sfondo.jpg"))));
        //im.setStyle("-fx-border-color: white");
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

        /*Button info = new Button("Info");
        info.setFont(Font.font("Sarpanch", 13));
        info.setStyle("-fx-background-color: black");
        info.setTextFill(Paint.valueOf("white"));*/

        /*Button map = new Button("Map");
        map.setFont(Font.font("Sarpanch", 13));
        map.setStyle("-fx-background-color: black");
        map.setTextFill(Paint.valueOf("white"));
        box.getChildren().addAll(info, map);*/
        //box.setStyle("-fx-background-color: white");

        pane.add(box,1,1);

        //Label lb2 = new Label();
        //lb2.setText("BellaZi");
        //pane.add(lb2, 0,1);
        int col = pane.getColumnCount();
        System.out.println(col);
    }

    /*public GridPane returnGridPane() {
        return this.pane;
    }*/
}
