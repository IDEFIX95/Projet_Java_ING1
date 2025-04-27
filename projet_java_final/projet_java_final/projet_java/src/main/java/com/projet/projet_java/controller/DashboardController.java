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

/**
 * Contrôleur du tableau de bord utilisateur.
 * Affiche les informations principales (points, dernier dépôt, nom),
 * et donne accès aux différentes fonctionnalités de l'application.
 */
public class DashboardController {

    @FXML private Label labelNomUtilisateur;   // Label pour afficher le nom de l'utilisateur
    @FXML private Label labelPointsFidelite;   // Label pour afficher les points de fidélité
    @FXML private Label labelDernierDepot;     // Label pour afficher les infos du dernier dépôt

    private final HistoriqueDepotDAO historiqueDepotDAO = new HistoriqueDepotDAO();

    /**
     * Initialise le tableau de bord à l'ouverture.
     */
    @FXML
    public void initialize() {
        refreshDashboard();

        // Ferme toutes les fenêtres ouvertes si on ferme celle du dashboard
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

        // Enregistre cette instance pour pouvoir la rafraîchir depuis d'autres contrôleurs
        UtilisateurConnecte.getInstance().setDashboardController(this);
    }

    /**
     * Rafraîchit les informations affichées sur le dashboard.
     */
    public void refreshDashboard() {
        Menage menage = UtilisateurConnecte.getInstance().getMenage();
        if (menage != null) {
            labelNomUtilisateur.setText("Bonjour, " + menage.getNom());
            labelPointsFidelite.setText("Points de fidélité : " + menage.getPointsFidelity());

            HistoriqueDepot dernierDepot = historiqueDepotDAO.getLastDepot(menage.getIdMenage());
            if (dernierDepot != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm");
                String date = dernierDepot.getHeureDepot().format(formatter);
                double quantite = Math.round(dernierDepot.getQuantiteDechets() * 100.0) / 100.0;
                labelDernierDepot.setText("Dernier dépôt : " + date + " - " + quantite + " kg");
            } else {
                labelDernierDepot.setText("Dernier dépôt : Aucun");
            }
        }
    }

    /**
     * Déconnecte l'utilisateur et retourne à la page de connexion.
     */
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

    /**
     * Ouvre la fenêtre pour déposer des déchets.
     */
    @FXML
    private void handleOuvrirDepot() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/depot_dechets.fxml"));
            Parent root = loader.load();

            DepotDechetsController depotController = loader.getController();
            depotController.setDashboardController(this);

            Stage stage = new Stage();
            stage.setTitle("Déposer Déchets");
            stage.setScene(new Scene(root));
            stage.show();

            FenetreManager.ajouterFenetre(stage);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ouvre la fenêtre pour modifier les informations du profil utilisateur.
     */
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

    /**
     * Ouvre l'historique des dépôts effectués par l'utilisateur.
     */
    @FXML
    private void goToHistorique() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/historique.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Historique des Dépôts");
            stage.setScene(new Scene(root));
            stage.show();

            FenetreManager.ajouterFenetre(stage);

        } catch (IOException e) {
            System.err.println("❌ Erreur ouverture historique : " + e.getMessage());
        }
    }

    /**
     * Ouvre la boutique pour utiliser ses points de fidélité en bons d'achat.
     */
    @FXML
    private void goToBonAchat() {
        FenetreManager.changerFenetre("/com/projet/projet_java/view/BonAchat.fxml");
    }

    /**
     * Permet aux autres classes de forcer un rafraîchissement du dashboard.
     */
    public void refresh() {
        refreshDashboard();
    }
}
