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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/projet/projet_java/view/connexion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Connexion");
        stage.setScene(scene);
        stage.show();

        // Décommenter si tu veux relancer certains tests :
         testInsertionCentreDeTri();
         testInsertionMenages();
         testPoubelleDAO();
         testDepotIncompatible();
         testAffichageDechets();
        testAffichageHistorique();
        testCentreDeTri();
         testGestionBonsAchat();
    }

    // === TESTS UTILES SI BESOIN ===

    private void testInsertionCentreDeTri() {
        CentreDeTriDAO dao = new CentreDeTriDAO();
        dao.insert(new CentreDeTri(0, "Centre Nord", "12 rue Industrielle"));
        System.out.println("✅ Centre inséré.");
    }

    private void testInsertionMenages() {
        MenageDAO dao = new MenageDAO();
        dao.insert(new Menage(0, "Jean Dupont", "42 rue Bleue", "jean@mail.fr", "azerty", "BADGE100", 0));
        dao.insert(new Menage(0, "Marie Test", "1 rue Verte", "testdepot@mail.fr", "pass123", "BADGE789", 0));
        System.out.println("✅ Ménages insérés.");
    }

    private void testPoubelleDAO() {
        PoubelleDAO dao = new PoubelleDAO();
        dao.insert(new PoubelleIntelligente(0, TypePoubelle.JAUNE, 30.0, 0.0, "Rue du Recyclage", 1));
        System.out.println("✅ Poubelle insérée.");
    }

    private void testDepotIncompatible() {
        MenageDAO menageDAO = new MenageDAO();
        DechetDAO dechetDAO = new DechetDAO();
        PoubelleDAO poubelleDAO = new PoubelleDAO();

        Menage menage = menageDAO.findByEmailAndPassword("jean@mail.fr", "azerty");
        if (menage == null) {
            System.err.println("❌ Ménage non trouvé.");
            return;
        }

        var poubelles = poubelleDAO.getByType(TypePoubelle.JAUNE);
        if (poubelles.isEmpty()) {
            System.err.println("❌ Aucune poubelle disponible.");
            return;
        }

        PoubelleIntelligente poubelle = poubelles.get(0);

        Corbeille corbeille = new Corbeille(777);
        Dechet dechetIncompatible = new Dechet(TypeDechet.VERRE, 1.0, poubelle.getIdPoubelle(), menage.getIdMenage());
        dechetIncompatible.setIdDechet(dechetDAO.insert(dechetIncompatible));
        corbeille.ajouterDechet(dechetIncompatible);

        boolean compatible = corbeille.getListeDechets().stream().allMatch(poubelle::verifierCompatibilite);

        if (!compatible) {
            System.err.println("🚫 Dépôt refusé : type de déchet incompatible avec la poubelle " + poubelle.getTypePoubelle());
        } else {
            System.out.println("❗️ Ce test aurait dû refuser un déchet incompatible.");
        }
    }

    private void testAffichageDechets() {
        new DechetDAO().getAll().forEach(System.out::println);
    }

    private void testAffichageHistorique() {
        new HistoriqueDepotDAO().getAllHistorique().forEach(System.out::println);
    }

    private void testCentreDeTri() {
        new PoubelleDAO().getByCentre(1).forEach(System.out::println);
    }

    private void testGestionBonsAchat() {
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
