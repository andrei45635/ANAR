module com.example.anar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.anar to javafx.fxml;
    exports com.example.anar;
}