package com.projet.projet_java.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Contrôleur pour la page d'accueil de l'application.
 * Permet de naviguer vers les écrans de connexion ou d'inscription.
 */
public class AccueilController {

    @FXML
    private Button boutonConnexion; // Bouton pour aller à la page de connexion

    @FXML
    private Button boutonInscription; // Bouton pour aller à la page d'inscription

    /**
     * Méthode appelée lors du clic sur "Se connecter".
     * Ouvre la fenêtre de connexion et ferme la page d'accueil.
     */
    @FXML
    private void handleConnexion() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/connexion.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Connexion");
            stage.setScene(new Scene(root));
            stage.show();

            // Fermer la fenêtre actuelle (accueil)
            Stage currentStage = (Stage) boutonConnexion.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode appelée lors du clic sur "S'inscrire".
     * Ouvre la fenêtre d'inscription et ferme la page d'accueil.
     */
    @FXML
    private void handleInscription() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/inscription.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Inscription");
            stage.setScene(new Scene(root));
            stage.show();

            // Fermer la fenêtre actuelle (accueil)
            Stage currentStage = (Stage) boutonInscription.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
