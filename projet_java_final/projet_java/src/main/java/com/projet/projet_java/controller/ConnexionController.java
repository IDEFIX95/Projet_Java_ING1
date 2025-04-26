package com.projet.projet_java.controller;

import com.projet.projet_java.dao.MenageDAO;
import com.projet.projet_java.model.Menage;
import com.projet.projet_java.session.UtilisateurConnecte;
import com.projet.projet_java.utils.FenetreManager;
import com.projet.projet_java.utils.HashUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnexionController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String motDePasse = passwordField.getText();

        if (email.equals("admin@tri.fr") && motDePasse.equals("1234")) {
            chargerScene("/com/projet/projet_java/view/dashboard.fxml", "Tableau de bord (Admin)");
            return;
        }

        // 💥 Hash du mot de passe entré
        String hashedPassword = HashUtils.hashPassword(motDePasse);

        MenageDAO dao = new MenageDAO();
        Menage menage = dao.findByEmailAndPassword(email, hashedPassword);

        if (menage != null) {
            UtilisateurConnecte.getInstance().setMenage(menage);
            chargerScene("/com/projet/projet_java/view/dashboard.fxml", "Tableau de bord - " + menage.getNom());
        } else {
            errorLabel.setText("Identifiants incorrects.");
            errorLabel.setVisible(true);
        }
    }

    private void chargerScene(String fxmlPath, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle(titre);
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("Erreur lors du chargement.");
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void handleOpenInscription() {
        FenetreManager.ouvrirFenetre("/com/projet/projet_java/view/inscription.fxml", "Inscription");
        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.close();
    }
}
