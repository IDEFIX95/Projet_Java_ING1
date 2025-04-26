package com.projet.projet_java.controller;

import com.projet.projet_java.dao.MenageDAO;
import com.projet.projet_java.model.Menage;
import com.projet.projet_java.utils.FenetreManager;
import com.projet.projet_java.utils.HashUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Random;

public class InscriptionController {

    @FXML private TextField nomField;
    @FXML private TextField adresseField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label inscriptionMessageLabel;

    private final MenageDAO menageDAO = new MenageDAO();
    private final Random random = new Random();

    @FXML
    private void handleRegister() {
        String nom = nomField.getText();
        String adresse = adresseField.getText();
        String email = emailField.getText();
        String motDePasse = passwordField.getText();

        if (nom.isEmpty() || adresse.isEmpty() || email.isEmpty() || motDePasse.isEmpty()) {
            inscriptionMessageLabel.setText("❌ Merci de remplir tous les champs.");
            return;
        }

        // 🎫 Génération du badge automatiquement
        String badge = "BADGE" + (100 + random.nextInt(900)); // BADGE100 à BADGE999

        // 💥 Hash du mot de passe
        String motDePasseHache = HashUtils.hashPassword(motDePasse);

        Menage nouveauMenage = new Menage(0, nom, adresse, email, motDePasseHache, badge, 0);

        if (menageDAO.insert(nouveauMenage)) {
            inscriptionMessageLabel.setText("✅ Inscription réussie !");
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                javafx.application.Platform.runLater(() -> {
                    Stage stage = (Stage) nomField.getScene().getWindow();
                    stage.close();
                    FenetreManager.ouvrirFenetre("/com/projet/projet_java/view/connexion.fxml", "Connexion");
                });
            }).start();
        } else {
            inscriptionMessageLabel.setText("❌ Erreur lors de l'inscription !");
        }
    }

    @FXML
    private void handleRetourConnexion() {
        Stage stage = (Stage) nomField.getScene().getWindow();
        stage.close();
        FenetreManager.ouvrirFenetre("/com/projet/projet_java/view/connexion.fxml", "Connexion");
    }
}
