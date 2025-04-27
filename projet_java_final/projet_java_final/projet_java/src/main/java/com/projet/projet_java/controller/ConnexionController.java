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

/**
 * Contrôleur de la page de connexion utilisateur ou administrateur.
 * Permet de vérifier les identifiants et d'ouvrir le tableau de bord adapté.
 */
public class ConnexionController {

    @FXML private TextField emailField;          // Champ pour entrer l'email
    @FXML private PasswordField passwordField;   // Champ pour entrer le mot de passe
    @FXML private Label errorLabel;               // Message d'erreur en cas d'échec de connexion

    /**
     * Gère la connexion utilisateur.
     * Si email/password = admin, ouvre directement le dashboard.
     * Sinon, vérifie en base de données après hash du mot de passe.
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String motDePasse = passwordField.getText();

        // Cas spécial : admin par défaut
        if (email.equals("admin@tri.fr") && motDePasse.equals("1234")) {
            chargerScene("/com/projet/projet_java/view/dashboard.fxml", "Tableau de bord (Admin)");
            return;
        }

        // Sinon, vérifie côté ménage normal
        String hashedPassword = HashUtils.hashPassword(motDePasse); // Hash du mot de passe
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

    /**
     * Charge une nouvelle fenêtre à partir du fichier FXML donné.
     * @param fxmlPath Chemin du fichier FXML à charger
     * @param titre    Titre de la nouvelle fenêtre
     */
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

    /**
     * Ouvre la fenêtre d'inscription et ferme celle de connexion actuelle.
     */
    @FXML
    private void handleOpenInscription() {
        FenetreManager.ouvrirFenetre("/com/projet/projet_java/view/inscription.fxml", "Inscription");

        Stage stage = (Stage) emailField.getScene().getWindow();
        stage.close();
    }
}
