package com.projet.projet_java.controller;

import com.projet.projet_java.dao.HistoriqueDepotDAO;
import com.projet.projet_java.model.HistoriqueDepot;
import com.projet.projet_java.model.Menage;
import com.projet.projet_java.session.UtilisateurConnecte;
import com.projet.projet_java.utils.FenetreManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class DashboardController {

    @FXML
    private Label labelNomUtilisateur;

    @FXML
    private Label labelPointsFidelite;

    @FXML
    private Label labelDernierDepot;

    private final HistoriqueDepotDAO historiqueDepotDAO = new HistoriqueDepotDAO();

    @FXML
    public void initialize() {
        Menage menage = UtilisateurConnecte.getInstance().getMenage();

        if (menage != null) {
            labelNomUtilisateur.setText("Bonjour, " + menage.getNom());
            labelPointsFidelite.setText("Points de fidélité : " + menage.getPointsFidelity());

            HistoriqueDepot dernierDepot = historiqueDepotDAO.getLastDepot(menage.getIdMenage());
            if (dernierDepot != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
                String date = dernierDepot.getHeureDepot().format(formatter);
                String typeDechet = dernierDepot.getTypeDechetPrincipal(); // à affiner si besoin
                double quantite = Math.round(dernierDepot.getQuantiteDechets() * 100.0) / 100.0;
                labelDernierDepot.setText("Dernier dépôt : " + date + " - " + quantite + "kg de " + typeDechet);
            } else {
                labelDernierDepot.setText("Dernier dépôt : aucun");
            }

        } else {
            labelNomUtilisateur.setText("Utilisateur inconnu");
            labelPointsFidelite.setText("");
            labelDernierDepot.setText("");
        }

        // Fermer toutes les autres fenêtres quand le dashboard se ferme
        labelNomUtilisateur.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.windowProperty().addListener((obsWin, oldWin, newWin) -> {
                    if (newWin != null) {
                        ((Stage) newWin).setOnCloseRequest(e -> FenetreManager.fermerToutes());
                        FenetreManager.ajouterFenetre((Stage) newWin);
                    }
                });
            }
        });
    }

    @FXML
    private void handleLogout() {
        try {
            UtilisateurConnecte.getInstance().seDeconnecter();
            FenetreManager.fermerToutes();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/connexion.fxml"));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Connexion");
            newStage.show();

            FenetreManager.ajouterFenetre(newStage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleOuvrirDepot() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/depot_dechets.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Déposer des déchets");
            stage.setScene(new Scene(root));
            stage.show();

            FenetreManager.ajouterFenetre(stage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleMonProfil() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/profil.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Mon Profil");
            stage.setScene(new Scene(root));
            stage.show();

            FenetreManager.ajouterFenetre(stage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void goToHistorique() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/historique.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Historique des Dépôts");
            stage.setScene(new Scene(root));
            stage.show();

            // Ajout dans le gestionnaire pour le suivi
            FenetreManager.ajouterFenetre(stage);

        } catch (IOException e) {
            System.err.println("❌ Erreur lors de l'ouverture de la fenêtre d'historique : " + e.getMessage());
        }
    }




}
