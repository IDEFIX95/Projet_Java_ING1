package com.projet.projet_java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AccueilController {

    @FXML
    private Button boutonConnexion; // Ajoute fx:id dans ton FXML
    @FXML
    private Button boutonInscription; // Ajoute fx:id dans ton FXML

    @FXML
    private void handleConnexion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/connexion.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Connexion");
            stage.setScene(new Scene(root));
            stage.show();

            // Fermer la fenêtre d'accueil
            Stage currentStage = (Stage) boutonConnexion.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleInscription() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/inscription.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Inscription");
            stage.setScene(new Scene(root));
            stage.show();

            // Fermer la fenêtre d'accueil
            Stage currentStage = (Stage) boutonInscription.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
