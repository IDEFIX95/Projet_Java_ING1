package com.projet.projet_java.controller;

import com.projet.projet_java.dao.MenageDAO;
import com.projet.projet_java.model.Menage;
import com.projet.projet_java.session.UtilisateurConnecte;
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
        String password = passwordField.getText();

        if (email.equals("admin@tri.fr") && password.equals("1234")) {
            chargerScene("/com/projet/projet_java/view/dashboard.fxml", "Tableau de bord (Admin)");
            return;
        }

        // Vérifie dans la base pour un ménage
        MenageDAO dao = new MenageDAO();
        Menage menage = dao.findByEmailAndPassword(email, password);

        if (menage != null) {
            // Stocke l'utilisateur connecté pour la session
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
}
