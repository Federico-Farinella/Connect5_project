module com.example.connect5_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;


    opens com.example.connect5 to javafx.fxml;
    exports com.example.connect5;
    exports com.example.connect5.GUIControllers;
    opens com.example.connect5.GUIControllers to javafx.fxml;
    exports com.example.connect5.models;
    opens com.example.connect5.models to javafx.fxml;
    exports com.example.connect5.bean;
    opens com.example.connect5.bean to javafx.fxml;
    exports com.example.connect5.models.booking_Decorator;
    opens com.example.connect5.models.booking_Decorator to javafx.fxml;
    exports com.example.connect5.history;
}