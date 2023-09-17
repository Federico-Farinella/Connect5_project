package com.example.connect5_project.take_booking.view.GUI;

import com.example.connect5_project.take_booking.bean.SearchResultBeanResponse;
import com.example.connect5_project.take_booking.bean.SearchResultsBeanRequest;
import com.example.connect5_project.take_booking.controller.BookingController;
import com.example.connect5_project.exceptions.SportCenterException;
import com.example.connect5_project.history.Navigate;
import com.example.connect5_project.utility.SportCenterElementsGUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SearchSportCentersGUI {
    private Navigate navigate;
    private BookingController bookingController;
    @FXML private TextField nomeCentroS;
    @FXML private TextField cittaCentroS;
    @FXML private TextField viaCentroS;
    @FXML
    private Button btnSearch;
    @FXML
    private Label errorLab;

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void back(ActionEvent e) throws Exception {
        Stage window;
        window = (Stage) ((Node) e.getSource()).getScene().getWindow();
        System.out.println(window);
        window.setScene(navigate.getPages().lastElement());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin()-1);
        navigate.pages.pop();
    }

    public void home(ActionEvent e) throws Exception {
        Stage window;
        int currentPagesAfterLogin = navigate.getCountPagesAfterLogin();
        for (int i = 0; i < currentPagesAfterLogin-1 ; i++) {
            navigate.getPages().pop();
        }
        navigate.setCountPagesAfterLogin(0);
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(navigate.getPages().lastElement());
        navigate.getPages().pop();

    }

    public void search(ActionEvent e) throws Exception {
        String name = nomeCentroS.getText();
        String city = cittaCentroS.getText();
        SearchResultBeanResponse searchResultsBeanOut;
        bookingController = new BookingController();
        SearchResultsBeanRequest bean_in = new SearchResultsBeanRequest();
        if (name.equals("") && city.equals("")) {
            errorLab.setText("All fields empty");
            errorLab.setVisible(true);
            return;
        } else if (!name.equals("") && city.equals("")) {
            bean_in.setSearchMode("Name");
            bean_in.setName(name);
            nomeCentroS.setText("");
        } else if (name.equals("")) {
            bean_in.setSearchMode("City");
            bean_in.setCity(city);
            cittaCentroS.setText("");
        } else {
            bean_in.setSearchMode("Name and city");
            bean_in.setName(name);
            bean_in.setCity(city);
            nomeCentroS.setText("");
            cittaCentroS.setText("");
        }
        errorLab.setVisible(false);
        navigate.pushPage(((Node) e.getSource()).getScene());
        navigate.setCountPagesAfterLogin(navigate.getCountPagesAfterLogin() + 1); // Aggiunto avendo introdotto countPagesAfterLogin
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SportCentersResults.fxml"));
        Parent root = loader.load();
        SportCentersResultsGUI controlGui = loader.getController();
        controlGui.setBookingController(this.getBookingController());
        controlGui.setNavigate(navigate);

        try {
            searchResultsBeanOut = this.getBookingController().searchCenters(bean_in);
            controlGui.setList(searchResultsBeanOut.getListOfCenters());
            SportCenterElementsGUI centerElement = new SportCenterElementsGUI(searchResultsBeanOut.getListOfCenters(), controlGui);
            ArrayList<GridPane> array = centerElement.getPanels();

            // Qui aggiungo uno per uno allo ScrollPane gli elementi trovati messi nella searchResultsBeanOut.
            // Voglio vedere se riesco ad indicizzare la lista per permettere click utente su un elemento e ottenerlo direttamente
            for (GridPane item : array) {
                controlGui.getBox().getChildren().add(item);
            }
        } catch (SportCenterException exception) {
            Label lab;
            if (exception.getMessage().equals("Not match"))
                lab = new Label("No results found for your query");
            else
                lab = new Label("Problem encountered. We are sorry for the inconvenience. Try later");
            lab.setPrefWidth(180.0);
            lab.setPrefHeight(70.0);
            lab.setAlignment(Pos.CENTER);
            lab.setTextAlignment(TextAlignment.CENTER);
            lab.setTextFill(Paint.valueOf( "rgb(179,252,252)"));
            lab.setFont(Font.font("Stalin One", 13));
            lab.setWrapText(true);
            controlGui.getBox().getChildren().add(lab);

        }

        System.out.println("Booking_controller: " + bookingController);
        Stage window =(Stage) btnSearch.getScene().getWindow();
        System.out.println("SearchSportCentersGui qui sono:3");
        window.setScene(new Scene(root));
        System.out.println("SearchSportCentersGui qui sono:4");

    }

    public BookingController getBookingController() {
        return bookingController;
    }

    public void setBookingController(BookingController bookingController) {
        this.bookingController = bookingController;
    }
}
