package com.projet.projet_java.controller;

import com.projet.projet_java.dao.CommerceDAO;
import com.projet.projet_java.model.Commerce;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

public class PartenairesController {

    @FXML
    private TableView<Commerce> partenairesTable;
    @FXML
    private TableColumn<Commerce, String> nomColumn;
    @FXML
    private TableColumn<Commerce, String> adresseColumn;

    private final CommerceDAO commerceDAO = new CommerceDAO();

    @FXML
    public void initialize() {
        partenairesTable.setItems(FXCollections.observableArrayList(commerceDAO.getAll()));

        nomColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNom()));
        adresseColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAdresse()));
    }

    @FXML
    private void handleRetourBonAchat() {
        Stage stage = (Stage) partenairesTable.getScene().getWindow();
        stage.close();
    }
}
