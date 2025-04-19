package com.projet.projet_java;

import com.projet.projet_java.model.*;
import com.projet.projet_java.dao.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/connexion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Connexion");
        stage.setScene(scene);
        stage.show();

        testInsertionCentreDeTri();
        testInsertionMenages();
        testPoubelleDAO();
        testDepotsMultiples();
        testDepotIncompatible(); // ✅ Ajout ici
        testAffichageDechets();
        testAffichageHistorique();
        testDechetsParMenage();
        testCentreDeTri();
        testGestionBonsAchat();
    }

    private void testInsertionCentreDeTri() {
        System.out.println("=== TEST INSERTION CENTRE DE TRI ===");
        CentreDeTriDAO dao = new CentreDeTriDAO();
        dao.insert(new CentreDeTri(0, "Centre Nord", "12 rue Industrielle"));
        System.out.println("✅ Centre inséré.");
    }

    private void testInsertionMenages() {
        System.out.println("=== TEST INSERTION MÉNAGES ===");
        MenageDAO dao = new MenageDAO();
        dao.insert(new Menage(0, "Jean Dupont", "42 rue Bleue", "jean@mail.fr", "azerty", "BADGE100", 0));
        dao.insert(new Menage(0, "Marie Test", "1 rue Verte", "testdepot@mail.fr", "pass123", "BADGE789", 0));
        System.out.println("✅ Ménages insérés.\n");
    }

    private void testPoubelleDAO() {
        System.out.println("=== TEST POUBELLE DAO ===");
        PoubelleDAO dao = new PoubelleDAO();
        dao.insert(new PoubelleIntelligente(0, TypePoubelle.JAUNE, 30.0, 0.0, "Rue du Recyclage", 1));
        System.out.println("✅ Poubelle insérée.\n");
    }

    private void testDepotsMultiples() {
        System.out.println("=== TEST DÉPÔTS MULTIPLES ===");

        MenageDAO menageDAO = new MenageDAO();
        PoubelleDAO poubelleDAO = new PoubelleDAO();
        DechetDAO dechetDAO = new DechetDAO();
        HistoriqueDepotDAO historiqueDAO = new HistoriqueDepotDAO();

        Menage menage = menageDAO.findByEmailAndPassword("testdepot@mail.fr", "pass123");
        int idPoubelle = poubelleDAO.getByType(TypePoubelle.JAUNE).get(0).getIdPoubelle();

        for (int i = 0; i < 3; i++) {
            Corbeille corbeille = new Corbeille(100 + i);
            Dechet d1 = new Dechet(TypeDechet.PLASTIQUE, 2.0, idPoubelle);
            Dechet d2 = new Dechet(TypeDechet.PLASTIQUE, 2.0, idPoubelle);

            d1.setIdDechet(dechetDAO.insert(d1));
            d2.setIdDechet(dechetDAO.insert(d2));
            corbeille.ajouterDechet(d1);
            corbeille.ajouterDechet(d2);

            double poids = corbeille.calculerPoidsTotal();
            int points = (int) (poids * 10);
            historiqueDAO.insertHistorique(new HistoriqueDepot(menage.getIdMenage(), idPoubelle, poids, points));

            menage.modifierPointsFidelity(points);
            menageDAO.updatePoints(menage.getIdMenage(), menage.getPointsFidelity());

            PoubelleIntelligente poubelleMaj = poubelleDAO.getById(idPoubelle);
            poubelleDAO.updateCapaciteActuelle(idPoubelle, poubelleMaj.getCapaciteActuelle() + poids);

            System.out.println("✅ Dépôt " + (i + 1) + " de " + poids + "kg, +" + points + " points.");
        }

        System.out.println("Points totaux : " + menageDAO.findByEmailAndPassword("testdepot@mail.fr", "pass123").getPointsFidelity());
        System.out.println("=== FIN DÉPÔTS ===\n");
    }

    private void testDepotIncompatible() {
        System.out.println("=== TEST DÉPÔT INCOMPATIBLE ===");

        MenageDAO menageDAO = new MenageDAO();
        DechetDAO dechetDAO = new DechetDAO();
        PoubelleDAO poubelleDAO = new PoubelleDAO();

        Menage menage = menageDAO.findByEmailAndPassword("testdepot@mail.fr", "pass123");
        PoubelleIntelligente poubelle = poubelleDAO.getByType(TypePoubelle.JAUNE).get(0);

        Corbeille corbeille = new Corbeille(777);
        Dechet dechetIncompatible = new Dechet(TypeDechet.VERRE, 1.0, poubelle.getIdPoubelle());
        dechetIncompatible.setIdDechet(dechetDAO.insert(dechetIncompatible));
        corbeille.ajouterDechet(dechetIncompatible);

        boolean compatible = corbeille.getListeDechets().stream().allMatch(poubelle::verifierCompatibilite);

        if (!compatible) {
            System.err.println("🚫 Dépôt refusé : type de déchet incompatible avec la poubelle " + poubelle.getTypePoubelle());
        } else {
            System.out.println("❗️ Ce test aurait dû refuser un déchet incompatible.");
        }

        System.out.println("=== FIN TEST INCOMPATIBILITÉ ===\n");
    }

    private void testAffichageDechets() {
        System.out.println("=== LISTE DES DÉCHETS ===");
        new DechetDAO().getAll().forEach(System.out::println);
    }

    private void testAffichageHistorique() {
        System.out.println("=== HISTORIQUE DÉPÔTS ===");
        new HistoriqueDepotDAO().getAllHistorique().forEach(System.out::println);
    }

    private void testDechetsParMenage() {
        System.out.println("=== DÉCHETS PAR MÉNAGE ===");
        new HistoriqueDepotDAO().getDechetsByMenage(2).forEach(System.out::println);
    }

    private void testCentreDeTri() {
        System.out.println("=== POUBELLES PAR CENTRE DE TRI ===");
        new PoubelleDAO().getByCentre(1).forEach(System.out::println);
    }

    private void testGestionBonsAchat() {
        System.out.println("=== TEST GÉNÉRATION BON D’ACHAT ===");
        MenageDAO menageDAO = new MenageDAO();
        BonAchatDAO bonDAO = new BonAchatDAO();
        CommerceDAO commerceDAO = new CommerceDAO();

        Menage menage = menageDAO.findByEmailAndPassword("testdepot@mail.fr", "pass123");
        if (menage == null) return;

        Commerce commerce = commerceDAO.getByNom("Super U");
        if (commerce == null) {
            commerce = new Commerce(0, "Super U", "Rue du Marché");
            commerceDAO.insert(commerce);
            commerce = commerceDAO.getByNom("Super U");
        }

        int points = 50;
        while (menage.getPointsFidelity() >= points) {
            BonAchat bon = new BonAchat(menage.getIdMenage(), commerce.getIdCommerce(), points, "Alimentation");
            if (bonDAO.insert(bon)) {
                menage.modifierPointsFidelity(-points);
                menageDAO.updatePoints(menage.getIdMenage(), menage.getPointsFidelity());
                System.out.println("🎁 Bon généré - " + points + " pts. Reste : " + menage.getPointsFidelity());
            }
        }

        System.out.println("📦 Bons du ménage " + menage.getNom() + " :");
        bonDAO.getByMenage(menage.getIdMenage()).forEach(System.out::println);

        System.out.println("\n🏪 Commerces disponibles :");
        commerceDAO.getAll().forEach(c -> System.out.println("🛍️ " + c.getNom() + " - " + c.getAdresse()));

        System.out.println("=== FIN TEST BONS ===\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
