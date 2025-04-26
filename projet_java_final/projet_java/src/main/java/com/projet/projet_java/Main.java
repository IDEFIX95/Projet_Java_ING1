package com.projet.projet_java;

import com.projet.projet_java.dao.*;
import com.projet.projet_java.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/accueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Accueil");
        stage.setScene(scene);
        stage.show();

        // 🔥 Insère automatiquement les commerces partenaires si pas présents
        initialiserCommerces();

        // Décommenter pour faire des tests si besoin
         testInsertionCentreDeTri();
         testPoubelleDAO();
    }

    // 🔥 Méthode pour insérer les commerces au démarrage
    private void initialiserCommerces() {
        CommerceDAO commerceDAO = new CommerceDAO();

        creerCommerceSiAbsent(commerceDAO, "Super U", "Rue du Marché");
        creerCommerceSiAbsent(commerceDAO, "Leroy Merlin", "Zone Commerciale Nord");
        creerCommerceSiAbsent(commerceDAO, "Biocoop", "Avenue Verte");
        creerCommerceSiAbsent(commerceDAO, "Netflix", "En ligne");
    }

    private void creerCommerceSiAbsent(CommerceDAO dao, String nom, String adresse) {
        if (dao.getByNom(nom) == null) {
            dao.insert(new Commerce(0, nom, adresse));
            System.out.println("✅ Commerce ajouté : " + nom);
        }
    }

    // === Insertions ===

    private void testInsertionCentreDeTri() {
        CentreDeTriDAO dao = new CentreDeTriDAO();
        dao.insert(new CentreDeTri(0, "Centre Nord", "12 rue Industrielle"));
        dao.insert(new CentreDeTri(0, "Centre Sud", "98 avenue du Soleil"));
        System.out.println("✅ Centre inséré.");
    }

    private void testPoubelleDAO() {
        PoubelleDAO dao = new PoubelleDAO();
        dao.insert(new PoubelleIntelligente(0, TypePoubelle.JAUNE, 30.0, 0.0, "Rue du Recyclage", 1));
        dao.insert(new PoubelleIntelligente(0, TypePoubelle.VERTE, 25.0, 0.0, "Avenue du Tri", 2));
        System.out.println("✅ Poubelle insérée.");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
