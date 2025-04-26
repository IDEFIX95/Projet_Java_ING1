package com.projet.projet_java.controller;

import com.projet.projet_java.dao.BonAchatDAO;
import com.projet.projet_java.dao.CommerceDAO;
import com.projet.projet_java.model.BonAchat;
import com.projet.projet_java.model.Commerce;
import com.projet.projet_java.session.UtilisateurConnecte;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class MesBonsController {

    @FXML
    private TableView<BonAchat> bonsTable;
    @FXML
    private TableColumn<BonAchat, String> commerceColumn;
    @FXML
    private TableColumn<BonAchat, String> categorieColumn;
    @FXML
    private TableColumn<BonAchat, String> pointsColumn;
    @FXML
    private TableColumn<BonAchat, String> dateCreationColumn;
    @FXML
    private TableColumn<BonAchat, String> dateExpirationColumn;

    private final BonAchatDAO bonAchatDAO = new BonAchatDAO();
    private final CommerceDAO commerceDAO = new CommerceDAO();

    @FXML
    public void initialize() {
        var menage = UtilisateurConnecte.getInstance().getMenage();
        if (menage == null) return;

        ObservableList<BonAchat> bons = FXCollections.observableArrayList(bonAchatDAO.getByMenage(menage.getIdMenage()));

        for (BonAchat bon : bons) {
            Commerce commerce = commerceDAO.getById(bon.getIdCommerce());
            if (commerce != null) {
                bon.setNomCommerce(commerce.getNom());
            } else {
                bon.setNomCommerce("Inconnu");
            }
        }

        bonsTable.setItems(bons);

        commerceColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNomCommerce())
        );
        categorieColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCategorie())
        );
        pointsColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getPointsUtilises()))
        );
        dateCreationColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(formatter(cellData.getValue().getDateCreation()))
        );
        dateExpirationColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(formatter(cellData.getValue().getDateExpiration()))
        );
    }

    private String formatter(java.time.LocalDate date) {
        if (date == null) return "Non défini";
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @FXML
    private void handleRetourBonAchat() {
        Stage stage = (Stage) bonsTable.getScene().getWindow();
        stage.close();
    }
}
