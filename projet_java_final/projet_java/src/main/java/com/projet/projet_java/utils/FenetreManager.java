package com.projet.projet_java.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FenetreManager {

    private static final List<Stage> fenetres = new ArrayList<>();

    // ➔ Ajouter une fenêtre ouverte à la liste
    public static void ajouterFenetre(Stage stage) {
        fenetres.add(stage);
    }

    // ➔ Fermer toutes les fenêtres ouvertes
    public static void fermerToutes() {
        for (Stage stage : fenetres) {
            if (stage.isShowing()) {
                stage.close();
            }
        }
        fenetres.clear();
    }

    // ➔ CHANGER une fenêtre vers une nouvelle vue
    public static void changerFenetre(String cheminFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(FenetreManager.class.getResource(cheminFXML));
            Parent root = loader.load();

            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.show();

            ajouterFenetre(newStage);
        } catch (Exception e) {
            System.err.println("❌ Erreur lors du changement de fenêtre : " + e.getMessage());
            e.printStackTrace();
        }
    }
    public static void ouvrirFenetre(String cheminFXML, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader(FenetreManager.class.getResource(cheminFXML));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle(titre);
            stage.setScene(scene);
            stage.show();
            ajouterFenetre(stage); // Pour pouvoir fermer toutes les fenêtres après
        } catch (Exception e) {
            System.err.println("❌ Erreur ouverture fenêtre : " + cheminFXML);
            e.printStackTrace();
        }
    }

}
