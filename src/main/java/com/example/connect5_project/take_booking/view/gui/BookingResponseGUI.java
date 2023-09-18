package com.example.connect5_project.take_booking.view.gui;

import com.example.connect5_project.history.Navigate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class BookingResponseGUI {
    @FXML
    private ImageView imageResponse;
    @FXML
    private Label labResponse;

    private Navigate navigate;


    public ImageView getImageResponse() {
        return this.imageResponse;
    }

    public void setImageResponse(boolean isConfirmed) {
        URL resource;
        if (isConfirmed)
            resource = getClass().getResource("/images/goal_booking.png");
        else
            resource = getClass().getResource("/images/failed_booking.png");
        if (resource != null) {
            Image image = new Image(resource.toString());
            this.getImageResponse().setImage(image);
        }
    }

    public Label getLabResponse() {
        return labResponse;
    }

    public void setLabResponse(boolean isConfirmed) {
        if (isConfirmed)
            labResponse.setText("Congratulations! Your booking has been successfull. You will be redirected to the home page in 5 seconds");
        else
            labResponse.setText("We apologize for the inconvenience. We are working to resolve the issue. Try later. You will be redirected to the home page in 5 seconds");

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    int currentPagesAfterLogin = navigate.getCountPagesAfterLogin();
                    for (int i = 0; i < currentPagesAfterLogin-1 ; i++) {
                        navigate.getPages().pop();
                    }
                    navigate.setCountPagesAfterLogin(0);
                    Stage window = (Stage) labResponse.getScene().getWindow();
                    window.setScene(navigate.getPages().peek());
                    navigate.getPages().pop();
                });
            }
        };

        timer.schedule(task, 5000);
    }

    public void setNavigate(Navigate navigate) {
        this.navigate = navigate;
    }

    public void home(ActionEvent e) throws Exception {
        Stage window;
        int currentPagesAfterLogin = navigate.getCountPagesAfterLogin();
        for (int i = 0; i < currentPagesAfterLogin-1 ; i++) {
            navigate.getPages().pop();
        }
        navigate.setCountPagesAfterLogin(0);
        window = (Stage)((Node) e.getSource()).getScene().getWindow();
        window.setScene(navigate.getPages().peek());
        navigate.getPages().pop();
    }


}
