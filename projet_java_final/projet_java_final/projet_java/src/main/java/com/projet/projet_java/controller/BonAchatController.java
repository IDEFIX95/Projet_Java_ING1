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

/**
 * Contrôleur de la page de gestion des Bons d'Achat.
 * Permet au ménage d'utiliser ses points pour obtenir des bons, voir ses bons ou voir les partenaires.
 */
public class BonAchatController {

    @FXML
    private Label labelPoints; // Affiche le nombre de points du ménage connecté

    private final MenageDAO menageDAO = new MenageDAO();
    private final BonAchatDAO bonAchatDAO = new BonAchatDAO();
    private final CommerceDAO commerceDAO = new CommerceDAO();

    private Menage menageConnecte;

    /**
     * Initialisation du contrôleur : affiche les points de fidélité de l'utilisateur.
     */
    @FXML
    public void initialize() {
        menageConnecte = UtilisateurConnecte.getInstance().getMenage();
        if (menageConnecte != null) {
            labelPoints.setText("Vos Points : " + menageConnecte.getPointsFidelity());
        } else {
            labelPoints.setText("Non connecté");
        }
    }

    /**
     * Ferme la fenêtre actuelle et retourne à l'accueil.
     */
    @FXML
    private void handleRetourAccueil() {
        Stage stage = (Stage) labelPoints.getScene().getWindow();
        stage.close();
    }

    /**
     * Utiliser un bon lié au commerce "Super U".
     */
    @FXML
    private void handleUtiliserBon1() {
        creerBon("Super U", 50, "Bon de 10€ pour 50€ d'achat", "Rue du Marché");
    }

    /**
     * Utiliser un bon lié au commerce "Leroy Merlin".
     */
    @FXML
    private void handleUtiliserBon2() {
        creerBon("Leroy Merlin", 65, "Remise de 15% rayon jardinage", "Zone Commerciale Nord");
    }

    /**
     * Utiliser un bon lié au commerce "Biocoop".
     */
    @FXML
    private void handleUtiliserBon3() {
        creerBon("Biocoop", 40, "Produit éco offert dès 30€ d'achat", "Avenue Verte");
    }

    /**
     * Utiliser un bon lié au commerce "Netflix".
     */
    @FXML
    private void handleUtiliserBon4() {
        creerBon("Netflix", 55, "1 mois d'abonnement offert", "En ligne");
    }

    /**
     * Crée un bon d'achat pour le commerce spécifié et retire les points du ménage.
     * @param nomCommerce Nom du commerce
     * @param coutPoints Coût du bon en points
     * @param description Description du bon
     * @param adresse Adresse du commerce
     */
    private void creerBon(String nomCommerce, int coutPoints, String description, String adresse) {
        if (menageConnecte.getPointsFidelity() < coutPoints) {
            showAlert("Pas assez de points pour utiliser ce bon.");
            return;
        }

        // Vérifie si le commerce existe sinon l'insère
        Commerce commerce = commerceDAO.getByNom(nomCommerce);
        if (commerce == null) {
            commerce = new Commerce(0, nomCommerce, adresse);
            commerceDAO.insert(commerce);
            commerce = commerceDAO.getByNom(nomCommerce);
        }

        // Création du bon
        BonAchat bon = new BonAchat(menageConnecte.getIdMenage(), commerce.getIdCommerce(), coutPoints, description);
        if (bonAchatDAO.insert(bon)) {
            // Déduction des points
            menageConnecte.modifierPointsFidelity(-coutPoints);
            menageDAO.updatePoints(menageConnecte.getIdMenage(), menageConnecte.getPointsFidelity());

            // Mise à jour visuelle immédiate du dashboard
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

    /**
     * Ouvre la page de visualisation des bons d'achat.
     */
    @FXML
    private void handleVoirMesBons() {
        FenetreManager.ouvrirFenetre("/com/projet/projet_java/view/MesBons.fxml", "Mes Bons d'Achat");
    }

    /**
     * Ouvre la page de visualisation des commerces partenaires.
     */
    @FXML
    private void handleVoirPartenaires() {
        FenetreManager.ouvrirFenetre("/com/projet/projet_java/view/Partenaire.fxml", "Commerces Partenaires");
    }

    /**
     * Affiche une boîte de dialogue d'information.
     * @param message Message à afficher
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
