package com.projet.projet_java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.projet.projet_java.dao.*;
import com.projet.projet_java.model.*;
import com.projet.projet_java.session.UtilisateurConnecte;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Contrôleur pour la gestion du dépôt de déchets par l'utilisateur.
 * Permet la sélection de déchets et l'affectation aux bonnes poubelles.
 */
public class DepotDechetsController {

    @FXML private ComboBox<PoubelleIntelligente> comboPoubelle; // Sélection de la poubelle
    @FXML private TextField badgeField;                         // Champ pour saisir le badge
    @FXML private CheckBox checkPlastique, checkVerre, checkCarton, checkMetal; // Cases types de déchets
    @FXML private Label labelMessage;                           // Message d'information (succès ou erreur)

    private final PoubelleDAO poubelleDAO = new PoubelleDAO();
    private final DechetDAO dechetDAO = new DechetDAO();
    private final HistoriqueDepotDAO historiqueDAO = new HistoriqueDepotDAO();
    private final MenageDAO menageDAO = new MenageDAO();

    private DashboardController dashboardController; // 🔥 Pour rafraîchir le Dashboard après dépôt
    private final Random random = new Random();

    /**
     * Initialise l'interface utilisateur du dépôt.
     */
    @FXML
    public void initialize() {
        chargerPoubellesDisponibles();
        comboPoubelle.setPromptText("Choisissez une poubelle");
    }

    /**
     * Gère le processus de dépôt de déchets dans une poubelle intelligente.
     */
    @FXML
    private void handleDepot() {
        PoubelleIntelligente poubelle = comboPoubelle.getValue();
        if (poubelle == null) {
            afficherMessage("❌ Veuillez sélectionner une poubelle.", false);
            return;
        }

        Menage menage = UtilisateurConnecte.getInstance().getMenage();
        if (menage == null) {
            afficherMessage("❌ Utilisateur non connecté.", false);
            return;
        }

        String badgeSaisi = badgeField.getText();
        if (badgeSaisi == null || !badgeSaisi.equals(menage.getBadgeAccess())) {
            afficherMessage("🚫 Badge incorrect. Dépôt refusé.", false);
            return;
        }

        // Création du panier (corbeille) des déchets
        Corbeille corbeille = new Corbeille(999);
        ajouterDechetSiCoche(checkPlastique, TypeDechet.PLASTIQUE, poubelle, corbeille);
        ajouterDechetSiCoche(checkVerre, TypeDechet.VERRE, poubelle, corbeille);
        ajouterDechetSiCoche(checkCarton, TypeDechet.CARTON, poubelle, corbeille);
        ajouterDechetSiCoche(checkMetal, TypeDechet.METAL, poubelle, corbeille);

        if (corbeille.getListeDechets().isEmpty()) {
            afficherMessage("⚠️ Aucun type de déchet sélectionné.", false);
            return;
        }

        double poidsTotal = corbeille.calculerPoidsTotal();
        poidsTotal = Math.round(poidsTotal * 100.0) / 100.0;

        // Vérification de la capacité de la poubelle
        if (poubelle.getCapaciteActuelle() + poidsTotal > poubelle.getCapaciteMaximale()) {
            afficherMessage("❌ Poubelle pleine, dépôt refusé.", false);
            return;
        }

        // Calcul des points par type de déchet
        int totalPoints = 0;
        for (Dechet d : corbeille.getListeDechets()) {
            if (poubelle.verifierCompatibilite(d)) {
                totalPoints += (int) (d.getPoids() * 5);
            } else {
                totalPoints += (int) (d.getPoids() * -2);
            }
        }

        // Enregistrement des déchets
        for (Dechet d : corbeille.getListeDechets()) {
            d.setIdDechet(dechetDAO.insert(d));
        }

        // Enregistrement dans l'historique
        historiqueDAO.insertHistorique(new HistoriqueDepot(
                menage.getIdMenage(),
                poubelle.getIdPoubelle(),
                poidsTotal,
                totalPoints
        ));

        // Mise à jour des points fidélité
        menage.modifierPointsFidelity(totalPoints);
        if (menage.getPointsFidelity() < 0) {
            menage.setPointsFidelity(0);
        }
        menageDAO.updatePoints(menage.getIdMenage(), menage.getPointsFidelity());

        // Mise à jour de la capacité de la poubelle
        double nouvelleCapacite = poubelle.getCapaciteActuelle() + poidsTotal;
        nouvelleCapacite = Math.round(nouvelleCapacite * 100.0) / 100.0;
        poubelleDAO.updateCapaciteActuelle(poubelle.getIdPoubelle(), nouvelleCapacite);

        // 🔥 Mise à jour Dashboard
        DashboardController dashboard = UtilisateurConnecte.getInstance().getDashboardController();
        if (dashboard != null) {
            dashboard.refreshDashboard();
        }

        // Message de confirmation
        if (totalPoints >= 0) {
            afficherMessage("✅ Dépôt réussi. Points gagnés : " + totalPoints, true);
        } else {
            afficherMessage("⚠️ Dépôt avec erreur(s). Points perdus : " + Math.abs(totalPoints), false);
        }

        // Réinitialisation des champs
        reinitialiserChamps();
        chargerPoubellesDisponibles();
    }

    /**
     * Ajoute un déchet au panier s'il est sélectionné.
     */
    private void ajouterDechetSiCoche(CheckBox box, TypeDechet type, PoubelleIntelligente poubelle, Corbeille corbeille) {
        if (box.isSelected()) {
            double poids = 0.5 + (3.0 - 0.5) * random.nextDouble();
            poids = Math.round(poids * 100.0) / 100.0;
            int idMenage = UtilisateurConnecte.getInstance().getMenage().getIdMenage();
            Dechet dechet = new Dechet(type, poids, poubelle.getIdPoubelle(), idMenage);
            corbeille.ajouterDechet(dechet);
        }
    }

    /**
     * Affiche un message dans l'interface utilisateur.
     */
    private void afficherMessage(String msg, boolean success) {
        labelMessage.setText(msg);
        labelMessage.setStyle(success ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
    }

    /**
     * Réinitialise tous les champs de sélection.
     */
    private void reinitialiserChamps() {
        comboPoubelle.getSelectionModel().clearSelection();
        comboPoubelle.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(PoubelleIntelligente item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "Choisissez une poubelle" : item.toString());
            }
        });

        checkPlastique.setSelected(false);
        checkVerre.setSelected(false);
        checkCarton.setSelected(false);
        checkMetal.setSelected(false);
    }

    /**
     * Charge toutes les poubelles encore disponibles (non pleines).
     */
    private void chargerPoubellesDisponibles() {
        List<PoubelleIntelligente> disponibles = poubelleDAO.getAll().stream()
                .filter(p -> p.getCapaciteActuelle() < p.getCapaciteMaximale())
                .collect(Collectors.toList());

        comboPoubelle.getItems().setAll(disponibles);
    }

    /**
     * Définit le DashboardController pour pouvoir forcer un rafraîchissement.
     */
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
