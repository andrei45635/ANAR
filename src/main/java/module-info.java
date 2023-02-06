module com.example.anar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.anar to javafx.fxml;
    exports com.example.anar;
    exports com.example.anar.controller;
    opens com.example.anar.controller to javafx.fxml;
    exports com.example.anar.domain;
    exports com.example.anar.utils.event;
    exports com.example.anar.service;
    exports com.example.anar.utils.observer;
    exports com.example.anar.repository.db;
}