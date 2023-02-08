module com.example.connect5_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;


    opens com.example.connect5Project to javafx.fxml;
    exports com.example.connect5Project;
    exports com.example.connect5Project.GUIControllers;
    opens com.example.connect5Project.GUIControllers to javafx.fxml;
    exports com.example.connect5Project.models;
    opens com.example.connect5Project.models to javafx.fxml;
    exports com.example.connect5Project.bean;
    opens com.example.connect5Project.bean to javafx.fxml;
    exports com.example.connect5Project.models.booking_Decorator;
    opens com.example.connect5Project.models.booking_Decorator to javafx.fxml;
    exports com.example.connect5Project.history;
}