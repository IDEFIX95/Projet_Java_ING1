package com.projet.projet_java.controller;

import com.projet.projet_java.dao.CommerceDAO;
import com.projet.projet_java.model.Commerce;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.beans.property.SimpleStringProperty;

/**
 * Contrôleur de la page "Partenaires".
 * Affiche la liste des commerces partenaires de l'application.
 */
public class PartenairesController {

    @FXML
    private TableView<Commerce> partenairesTable;
    @FXML
    private TableColumn<Commerce, String> nomColumn;
    @FXML
    private TableColumn<Commerce, String> adresseColumn;

    private final CommerceDAO commerceDAO = new CommerceDAO();

    /**
     * Initialise la TableView avec tous les commerces partenaires de la base de données.
     */
    @FXML
    public void initialize() {
        // Remplit la table avec les commerces
        partenairesTable.setItems(FXCollections.observableArrayList(commerceDAO.getAll()));

        // Remplit dynamiquement chaque colonne
        nomColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNom())
        );
        adresseColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAdresse())
        );
    }

    /**
     * Ferme la fenêtre actuelle et retourne à la fenêtre précédente.
     */
    @FXML
    private void handleRetourBonAchat() {
        Stage stage = (Stage) partenairesTable.getScene().getWindow();
        stage.close();
    }
}
