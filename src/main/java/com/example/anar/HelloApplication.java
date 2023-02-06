package com.example.anar;

import com.example.anar.controller.HelloController;
import com.example.anar.repository.db.RiverRepoDB;
import com.example.anar.repository.db.SettlementRepoDB;
import com.example.anar.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        HelloController controller = fxmlLoader.getController();
        controller.setService(new Service(new RiverRepoDB(), new SettlementRepoDB()));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}