package com.projet.projet_java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.projet.projet_java.dao.*;
import com.projet.projet_java.model.*;
import com.projet.projet_java.session.UtilisateurConnecte;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DepotDechetsController {

    @FXML private ComboBox<PoubelleIntelligente> comboPoubelle;
    @FXML private TextField badgeField;
    @FXML private CheckBox checkPlastique, checkVerre, checkCarton, checkMetal;
    @FXML private Label labelMessage;

    private final PoubelleDAO poubelleDAO = new PoubelleDAO();
    private final DechetDAO dechetDAO = new DechetDAO();
    private final HistoriqueDepotDAO historiqueDAO = new HistoriqueDepotDAO();
    private final MenageDAO menageDAO = new MenageDAO();

    private DashboardController dashboardController;

    private final Random random = new Random();

    @FXML
    public void initialize() {
        chargerPoubellesDisponibles();
        comboPoubelle.setPromptText("Choisissez une poubelle");
    }

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

        if (poubelle.getCapaciteActuelle() + poidsTotal > poubelle.getCapaciteMaximale()) {
            afficherMessage("❌ Poubelle pleine, dépôt refusé.", false);
            return;
        }

        int totalPoints = 0;
        for (Dechet d : corbeille.getListeDechets()) {
            if (poubelle.verifierCompatibilite(d)) {
                totalPoints += (int) (d.getPoids() * 5);
            } else {
                totalPoints += (int) (d.getPoids() * -2);
            }
        }

        for (Dechet d : corbeille.getListeDechets()) {
            d.setIdDechet(dechetDAO.insert(d));
        }

        historiqueDAO.insertHistorique(new HistoriqueDepot(
                menage.getIdMenage(),
                poubelle.getIdPoubelle(),
                poidsTotal,
                totalPoints
        ));

        menage.modifierPointsFidelity(totalPoints);
        if (menage.getPointsFidelity() < 0) {
            menage.setPointsFidelity(0);
        }
        menageDAO.updatePoints(menage.getIdMenage(), menage.getPointsFidelity());

        double nouvelleCapacite = poubelle.getCapaciteActuelle() + poidsTotal;
        nouvelleCapacite = Math.round(nouvelleCapacite * 100.0) / 100.0;
        poubelleDAO.updateCapaciteActuelle(poubelle.getIdPoubelle(), nouvelleCapacite);

        // ✅ Mise à jour Dashboard
        DashboardController dashboard = UtilisateurConnecte.getInstance().getDashboardController();
        if (dashboard != null) {
            dashboard.refreshDashboard();
        }

        // ✅ Message + Réinitialisation
        if (totalPoints >= 0) {
            afficherMessage("✅ Dépôt réussi. Points gagnés : " + totalPoints, true);
        } else {
            afficherMessage("⚠️ Dépôt avec erreur(s). Points perdus : " + Math.abs(totalPoints), false);
        }

        reinitialiserChamps();
        chargerPoubellesDisponibles();
    }

    private void ajouterDechetSiCoche(CheckBox box, TypeDechet type, PoubelleIntelligente poubelle, Corbeille corbeille) {
        if (box.isSelected()) {
            double poids = 0.5 + (3.0 - 0.5) * random.nextDouble();
            poids = Math.round(poids * 100.0) / 100.0;
            int idMenage = UtilisateurConnecte.getInstance().getMenage().getIdMenage();
            Dechet dechet = new Dechet(type, poids, poubelle.getIdPoubelle(), idMenage);
            corbeille.ajouterDechet(dechet);
        }
    }

    private void afficherMessage(String msg, boolean success) {
        labelMessage.setText(msg);
        labelMessage.setStyle(success ? "-fx-text-fill: green;" : "-fx-text-fill: red;");
    }

    // 🔥 Cette méthode remet tous les champs à zéro (combo + cases cochées)
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

    private void chargerPoubellesDisponibles() {
        List<PoubelleIntelligente> disponibles = poubelleDAO.getAll().stream()
                .filter(p -> p.getCapaciteActuelle() < p.getCapaciteMaximale())
                .collect(Collectors.toList());

        comboPoubelle.getItems().setAll(disponibles);
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

}
