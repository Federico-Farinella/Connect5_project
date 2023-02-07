module com.example.connect5_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;


    opens com.example.connect5_project to javafx.fxml;
    exports com.example.connect5_project;
    exports com.example.connect5_project.GUIControllers;
    opens com.example.connect5_project.GUIControllers to javafx.fxml;
    exports com.example.connect5_project.models;
    opens com.example.connect5_project.models to javafx.fxml;
    exports com.example.connect5_project.bean;
    opens com.example.connect5_project.bean to javafx.fxml;
    exports com.example.connect5_project.models.booking_Decorator;
    opens com.example.connect5_project.models.booking_Decorator to javafx.fxml;
    exports com.example.connect5_project.history;
}