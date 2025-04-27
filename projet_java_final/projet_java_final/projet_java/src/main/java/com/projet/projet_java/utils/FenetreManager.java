package com.projet.projet_java.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire pour gérer l'ouverture, la fermeture
 * et le changement de fenêtres dans l'application JavaFX.
 */
public class FenetreManager {

    // Liste de toutes les fenêtres ouvertes
    private static final List<Stage> fenetres = new ArrayList<>();

    /**
     * ➔ Ajouter une fenêtre ouverte à la liste.
     * @param stage Fenêtre à ajouter
     */
    public static void ajouterFenetre(Stage stage) {
        fenetres.add(stage);
    }

    /**
     * ➔ Fermer toutes les fenêtres actuellement ouvertes.
     */
    public static void fermerToutes() {
        for (Stage stage : fenetres) {
            if (stage.isShowing()) {
                stage.close();
            }
        }
        fenetres.clear();
    }

    /**
     * ➔ Changer la vue actuelle vers une nouvelle fenêtre FXML.
     * @param cheminFXML Chemin du fichier FXML à charger
     */
    public static void changerFenetre(String cheminFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(FenetreManager.class.getResource(cheminFXML));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();

            ajouterFenetre(newStage);
        } catch (Exception e) {
            System.err.println(" Erreur lors du changement de fenêtre : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * ➔ Ouvrir une nouvelle fenêtre avec un titre personnalisé.
     * @param cheminFXML Chemin du fichier FXML
     * @param titre Titre de la nouvelle fenêtre
     */
    public static void ouvrirFenetre(String cheminFXML, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader(FenetreManager.class.getResource(cheminFXML));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(titre);
            stage.setScene(scene);
            stage.show();

            ajouterFenetre(stage); // Pour pouvoir les fermer toutes ensuite
        } catch (Exception e) {
            System.err.println(" Erreur ouverture fenêtre : " + cheminFXML);
            e.printStackTrace();
        }
    }
}
