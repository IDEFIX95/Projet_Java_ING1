package com.projet.projet_java;

import com.projet.projet_java.dao.*;
import com.projet.projet_java.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe principale du projet Tri Sélectif.
 * Lance l'application JavaFX et initialise les données nécessaires.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // ➔ Chargement de la scène d'accueil
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Accueil");
        stage.setScene(scene);
        stage.show();

        // 🔥 Insertion automatique des commerces partenaires si nécessaire
        initialiserCommerces();

        // ✅ Insertion de données de test pour les centres de tri et poubelles
        testInsertionCentreDeTri();
        testPoubelleDAO();
    }

    /**
     * 🔥 Initialise les commerces partenaires au lancement.
     */
    private void initialiserCommerces() {
        CommerceDAO commerceDAO = new CommerceDAO();

        creerCommerceSiAbsent(commerceDAO, "Super U", "Rue du Marché");
        creerCommerceSiAbsent(commerceDAO, "Leroy Merlin", "Zone Commerciale Nord");
        creerCommerceSiAbsent(commerceDAO, "Biocoop", "Avenue Verte");
        creerCommerceSiAbsent(commerceDAO, "Netflix", "En ligne");
    }

    /**
     * 🔹 Crée un commerce s'il n'existe pas déjà dans la base.
     * @param dao DAO pour accéder aux commerces
     * @param nom Nom du commerce
     * @param adresse Adresse du commerce
     */
    private void creerCommerceSiAbsent(CommerceDAO dao, String nom, String adresse) {
        if (dao.getByNom(nom) == null) {
            dao.insert(new Commerce(0, nom, adresse));
            System.out.println("✅ Commerce ajouté : " + nom);
        }
    }

    /**
     * 🔹 Insère des centres de tri pour les tests.
     */
    private void testInsertionCentreDeTri() {
        CentreDeTriDAO dao = new CentreDeTriDAO();
        dao.insert(new CentreDeTri(0, "Centre Nord", "12 rue Industrielle"));
        dao.insert(new CentreDeTri(0, "Centre Sud", "98 avenue du Soleil"));
        System.out.println("✅ Centres de tri insérés.");
    }

    /**
     * 🔹 Insère des poubelles pour les tests.
     */
    private void testPoubelleDAO() {
        PoubelleDAO dao = new PoubelleDAO();
        dao.insert(new PoubelleIntelligente(0, TypePoubelle.JAUNE, 30.0, 0.0, "Rue du Recyclage", 1));
        dao.insert(new PoubelleIntelligente(0, TypePoubelle.VERTE, 25.0, 0.0, "Avenue du Tri", 2));
        System.out.println("✅ Poubelles insérées.");
    }

    /**
     * Point d'entrée principal du programme.
     * @param args Arguments de lancement
     */
    public static void main(String[] args) {
        launch(args);
    }
}
