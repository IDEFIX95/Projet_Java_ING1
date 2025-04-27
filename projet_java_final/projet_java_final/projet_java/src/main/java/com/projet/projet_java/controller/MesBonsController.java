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

/**
 * Contrôleur de la page "Mes Bons d'Achat".
 * Permet d'afficher la liste des bons acquis par l'utilisateur connecté.
 */
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

    /**
     * Initialise la TableView avec les bons d'achat du ménage connecté.
     */
    @FXML
    public void initialize() {
        var menage = UtilisateurConnecte.getInstance().getMenage();
        if (menage == null) return;

        // Récupère tous les bons du ménage
        ObservableList<BonAchat> bons = FXCollections.observableArrayList(
                bonAchatDAO.getByMenage(menage.getIdMenage())
        );

        // Mise à jour pour chaque bon : on complète avec le nom du commerce
        for (BonAchat bon : bons) {
            Commerce commerce = commerceDAO.getById(bon.getIdCommerce());
            if (commerce != null) {
                bon.setNomCommerce(commerce.getNom());
            } else {
                bon.setNomCommerce("Inconnu");
            }
        }

        bonsTable.setItems(bons);

        // Définir comment remplir chaque colonne
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

    /**
     * Formate une date en format "dd/MM/yyyy" pour affichage.
     * @param date La date à formater
     * @return Une chaîne de caractères formatée
     */
    private String formatter(java.time.LocalDate date) {
        if (date == null) return "Non défini";
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Ferme la fenêtre actuelle et retourne à l'écran précédent.
     */
    @FXML
    private void handleRetourBonAchat() {
        Stage stage = (Stage) bonsTable.getScene().getWindow();
        stage.close();
    }
}
