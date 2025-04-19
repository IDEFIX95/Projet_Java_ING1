package com.projet.projet_java;

import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainTest {

    public static void main(String[] args) {
        // 🔁 Forcer la base de données de test
        DatabaseConfig.setUseTestDatabase(true);

        // 1️⃣ Lancer les tests JUnit du package
        System.out.println("🚀 Lancement des tests JUnit :");

        // ✅ Réinitialiser la base de test avant d’exécuter les tests
        //SQLExecutor.runScript("Database/tri_selectifTest.sql");

        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectPackage("com.projet.projet_java"))
                .build();

        Launcher launcher = LauncherFactory.create();
        launcher.execute(request);

        // 2️⃣ Afficher les données des tables après les tests
        System.out.println("\n📊 Affichage du contenu des tables dans tri_selectifTest :");

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Connexion échouée à la base de données.");
                return;
            }

            System.out.println("✅ Connexion à la base MySQL [tri_selectifTest] réussie !");
            String[] tables = {
                    "Dechet", "Menage", "PoubelleIntelligente", "HistoriqueDepot",
                    "Corbeille", "Commerce", "ContratPartenariat", "CentreDeTri",
                    "Categorie", "BonAchat"
            };

            for (String table : tables) {
                System.out.println("\n📂 Table : " + table);
                afficherContenuTable(conn, table);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erreur SQL globale : " + e.getMessage());
        }
    }

    private static void afficherContenuTable(Connection conn, String tableName) {
        String query = "SELECT * FROM " + tableName;
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            int columnCount = rs.getMetaData().getColumnCount();

            if (!rs.isBeforeFirst()) {
                System.out.println("   (aucune donnée)");
                return;
            }

            while (rs.next()) {
                StringBuilder row = new StringBuilder("   ➤ ");
                for (int i = 1; i <= columnCount; i++) {
                    String column = rs.getMetaData().getColumnLabel(i);
                    String value = rs.getString(i);
                    row.append(column).append("=").append(value).append("  ");
                }
                System.out.println(row);
            }

        } catch (SQLException e) {
            System.out.println("   ⚠️ Erreur lors de l'accès à la table '" + tableName + "' : " + e.getMessage());
        }
    }
}
