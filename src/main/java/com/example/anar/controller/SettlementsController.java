package com.example.anar.controller;

import com.example.anar.domain.Settlement;
import com.example.anar.service.Service;
import com.example.anar.utils.event.EntityChangeEvent;
import com.example.anar.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SettlementsController implements Observer<EntityChangeEvent> {
    private Service service;
    private final ObservableList<Settlement> lowRiskModel = FXCollections.observableArrayList();
    private final ObservableList<Settlement> mediumRiskModel = FXCollections.observableArrayList();
    private final ObservableList<Settlement> highRiskModel = FXCollections.observableArrayList();
    @FXML
    private TableView<Settlement> lowRiskTableView;
    @FXML
    private TableColumn<Settlement, String> lowRiskSettlementName;
    @FXML
    private TableColumn<Settlement, Integer> lowRiskRiverID;
    @FXML
    private TableColumn<Settlement, Integer> lowRiskCMDR;
    @FXML
    private TableColumn<Settlement, Integer> lowRiskCMA;
    @FXML
    private TableView<Settlement> mediumRiskTableView;
    @FXML
    private TableColumn<Settlement, String> mediumRiskSettlementName;
    @FXML
    private TableColumn<Settlement, Integer> mediumRiskRiverID;
    @FXML
    private TableColumn<Settlement, Integer> mediumRiskCMDR;
    @FXML
    private TableColumn<Settlement, Integer> mediumRiskCMA;
    @FXML
    private TableView<Settlement> highRiskTableView;
    @FXML
    private TableColumn<Settlement, String> highRiskSettlementName;
    @FXML
    private TableColumn<Settlement, Integer> highRiskRiverID;
    @FXML
    private TableColumn<Settlement, Integer> highRiskCMDR;
    @FXML
    private TableColumn<Settlement, Integer> highRiskCMA;

    public void setService(Service service){
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize(){
        lowRiskSettlementName.setCellValueFactory(new PropertyValueFactory<>("name"));
        lowRiskRiverID.setCellValueFactory(new PropertyValueFactory<>("riverID"));
        lowRiskCMDR.setCellValueFactory(new PropertyValueFactory<>("cmdr"));
        lowRiskCMA.setCellValueFactory(new PropertyValueFactory<>("cma"));
        System.out.println("random");
        lowRiskTableView.setItems(lowRiskModel);
        System.out.println("random");
        mediumRiskSettlementName.setCellValueFactory(new PropertyValueFactory<>("name"));
        mediumRiskRiverID.setCellValueFactory(new PropertyValueFactory<>("riverID"));
        mediumRiskCMDR.setCellValueFactory(new PropertyValueFactory<>("cmdr"));
        mediumRiskCMA.setCellValueFactory(new PropertyValueFactory<>("cma"));
        System.out.println("random1");
        mediumRiskTableView.setItems(mediumRiskModel);
        highRiskSettlementName.setCellValueFactory(new PropertyValueFactory<>("name"));
        highRiskRiverID.setCellValueFactory(new PropertyValueFactory<>("riverID"));
        highRiskCMDR.setCellValueFactory(new PropertyValueFactory<>("cmdr"));
        highRiskCMA.setCellValueFactory(new PropertyValueFactory<>("cma"));
        highRiskTableView.setItems(highRiskModel);
    }

    private void initModel() {
        lowRiskModel.setAll(service.getLowRiskSettlements());
        mediumRiskModel.setAll(service.getMediumRiskSettlements());
        highRiskModel.setAll(service.getHighRiskSettlements());
    }

    @Override
    public void update(EntityChangeEvent t) {
        initModel();
    }
}
