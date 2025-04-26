package com.projet.projet_java.controller;

import com.projet.projet_java.dao.BonAchatDAO;
import com.projet.projet_java.dao.CommerceDAO;
import com.projet.projet_java.dao.MenageDAO;
import com.projet.projet_java.model.BonAchat;
import com.projet.projet_java.model.Commerce;
import com.projet.projet_java.model.Menage;
import com.projet.projet_java.session.UtilisateurConnecte;
import com.projet.projet_java.utils.FenetreManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BonAchatController {

    @FXML
    private Label labelPoints;

    private final MenageDAO menageDAO = new MenageDAO();
    private final BonAchatDAO bonAchatDAO = new BonAchatDAO();
    private final CommerceDAO commerceDAO = new CommerceDAO();

    private Menage menageConnecte;

    @FXML
    public void initialize() {
        menageConnecte = UtilisateurConnecte.getInstance().getMenage();
        if (menageConnecte != null) {
            labelPoints.setText("Vos Points : " + menageConnecte.getPointsFidelity());
        } else {
            labelPoints.setText("Non connecté");
        }
    }

    @FXML
    private void handleRetourAccueil() {
        Stage stage = (Stage) labelPoints.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleUtiliserBon1() {
        creerBon("Super U", 50, "Bon de 10€ pour 50€ d'achat", "Rue du Marché");
    }

    @FXML
    private void handleUtiliserBon2() {
        creerBon("Leroy Merlin", 65, "Remise de 15% rayon jardinage", "Zone Commerciale Nord");
    }

    @FXML
    private void handleUtiliserBon3() {
        creerBon("Biocoop", 40, "Produit éco offert dès 30€ d'achat", "Avenue Verte");
    }

    @FXML
    private void handleUtiliserBon4() {
        creerBon("Netflix", 55, "1 mois d'abonnement offert", "En ligne");
    }

    private void creerBon(String nomCommerce, int coutPoints, String description, String adresse) {
        if (menageConnecte.getPointsFidelity() < coutPoints) {
            showAlert("Pas assez de points pour utiliser ce bon.");
            return;
        }

        Commerce commerce = commerceDAO.getByNom(nomCommerce);
        if (commerce == null) {
            commerce = new Commerce(0, nomCommerce, adresse);
            commerceDAO.insert(commerce);
            commerce = commerceDAO.getByNom(nomCommerce);
        }

        BonAchat bon = new BonAchat(menageConnecte.getIdMenage(), commerce.getIdCommerce(), coutPoints, description);
        if (bonAchatDAO.insert(bon)) {
            menageConnecte.modifierPointsFidelity(-coutPoints);
            menageDAO.updatePoints(menageConnecte.getIdMenage(), menageConnecte.getPointsFidelity());

            // 🔥 Mise à jour du Dashboard après utilisation du bon
            DashboardController dashboard = UtilisateurConnecte.getInstance().getDashboardController();
            if (dashboard != null) {
                dashboard.refresh();
            }

            showAlert("🎉 Bon d'achat généré avec succès !");
            labelPoints.setText("Vos Points : " + menageConnecte.getPointsFidelity());
        } else {
            showAlert("Erreur lors de la génération du bon d'achat.");
        }
    }


    @FXML
    private void handleVoirMesBons() {
        FenetreManager.ouvrirFenetre("/com/projet/projet_java/view/MesBons.fxml", "Mes Bons d'Achat");
    }

    @FXML
    private void handleVoirPartenaires() {
        FenetreManager.ouvrirFenetre("/com/projet/projet_java/view/Partenaire.fxml", "Commerces Partenaires");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
