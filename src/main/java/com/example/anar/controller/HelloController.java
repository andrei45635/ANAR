package com.example.anar.controller;

import com.example.anar.HelloApplication;
import com.example.anar.domain.River;
import com.example.anar.repository.db.RiverRepoDB;
import com.example.anar.repository.db.SettlementRepoDB;
import com.example.anar.service.Service;
import com.example.anar.utils.event.EntityChangeEvent;
import com.example.anar.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloController implements Observer<EntityChangeEvent> {
    private Service service;
    private final ObservableList<River> riverModels = FXCollections.observableArrayList();
    @FXML
    private TextField updateMeanTF;
    @FXML
    public TableView<River> riverTableView;
    @FXML
    public TableColumn<River, String> riverNameColumn;
    @FXML
    public TableColumn<River, Integer> riverMeanColumn;

    public void setService(Service service) throws IOException {
        this.service = service;
        service.addObserver(this);
        initModel();

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("settlements-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Hello!");
        stage.setScene(scene);

        SettlementsController controller = fxmlLoader.getController();
        controller.setService(service);

        stage.show();

    }

    @FXML
    public void initialize(){
        riverNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        riverMeanColumn.setCellValueFactory(new PropertyValueFactory<>("mean"));

        riverTableView.setItems(riverModels);
    }

    public void initModel(){
        List<River> rivers = service.getAllRivers();
        riverModels.setAll(rivers);
    }

    @Override
    public void update(EntityChangeEvent t) {
        initModel();
    }

    @FXML
    private void onClickUpdateMean(ActionEvent actionEvent) {
        River selectedRiver = riverTableView.getSelectionModel().getSelectedItem();
        int newMean = Integer.parseInt(updateMeanTF.getText());
        service.updateRiver(selectedRiver, newMean);
        initModel();
    }
}