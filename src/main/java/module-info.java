module com.example.connect5_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;


    opens com.example.connect_five to javafx.fxml;
    exports com.example.connect_five;
    exports com.example.connect_five.GUIControllers;
    opens com.example.connect_five.GUIControllers to javafx.fxml;
    exports com.example.connect_five.models;
    opens com.example.connect_five.models to javafx.fxml;
    exports com.example.connect_five.bean;
    opens com.example.connect_five.bean to javafx.fxml;
    exports com.example.connect_five.models.booking_Decorator;
    opens com.example.connect_five.models.booking_Decorator to javafx.fxml;
    exports com.example.connect_five.history;
}