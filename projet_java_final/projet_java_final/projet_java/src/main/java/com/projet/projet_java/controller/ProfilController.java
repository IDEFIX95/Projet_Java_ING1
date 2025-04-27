package com.projet.projet_java.controller;

import com.projet.projet_java.dao.MenageDAO;
import com.projet.projet_java.model.Menage;
import com.projet.projet_java.session.UtilisateurConnecte;
import com.projet.projet_java.utils.FenetreManager;
import com.projet.projet_java.utils.HashUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Contrôleur de la page Profil utilisateur.
 * Permet de visualiser et modifier les informations du ménage connecté.
 */
public class ProfilController {

    @FXML private TextField nomField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private TextField adresseField;
    @FXML private Label pointsLabel;
    @FXML private Label badgeLabel;

    private final MenageDAO menageDAO = new MenageDAO();

    /**
     * Initialise la page avec les données de l'utilisateur connecté.
     */
    @FXML
    public void initialize() {
        Menage menage = UtilisateurConnecte.getInstance().getMenage();

        if (menage != null) {
            nomField.setText(menage.getNom());
            emailField.setText(menage.getEmail());
            passwordField.setText(""); // ✅ Champ vide pour sécurité
            adresseField.setText(menage.getAdresse());
            pointsLabel.setText(String.valueOf(menage.getPointsFidelity()));
            badgeLabel.setText(menage.getBadgeAccess());
        }
    }

    /**
     * Met à jour les informations du ménage dans la base de données.
     * Hache le nouveau mot de passe uniquement s'il est modifié.
     */
    @FXML
    private void handleUpdate() {
        Menage menage = UtilisateurConnecte.getInstance().getMenage();

        if (menage != null) {
            menage.setNom(nomField.getText());
            menage.setEmail(emailField.getText());
            menage.setAdresse(adresseField.getText());

            // 🎯 Mise à jour uniquement si un nouveau mot de passe est saisi
            if (!passwordField.getText().isEmpty()) {
                String nouveauHash = HashUtils.hashPassword(passwordField.getText());
                menage.setMotDePasse(nouveauHash);
            }

            boolean updated = menageDAO.updateMenage(menage);
            if (updated) {
                showAlert(Alert.AlertType.INFORMATION, "✅ Mise à jour réussie !");
            } else {
                showAlert(Alert.AlertType.ERROR, "❌ Une erreur est survenue lors de la mise à jour.");
            }
        }
    }

    /**
     * Ouvre la fenêtre des statistiques utilisateur.
     */
    @FXML
    private void goToStats() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/statistiques.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Mes statistiques");
            stage.setScene(new Scene(root));
            stage.show();

            FenetreManager.ajouterFenetre(stage);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Erreur lors de l'ouverture des statistiques.");
        }
    }

    /**
     * Ferme la fenêtre Profil pour revenir au Dashboard.
     */
    @FXML
    private void handleRetourDashboard() {
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
    }

    /**
     * Affiche une alerte personnalisée.
     * @param type Type d'alerte (info, erreur, etc.)
     * @param msg Message à afficher.
     */
    private void showAlert(Alert.AlertType type, String msg) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
