package com.projet.projet_java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseViewerTri_selectif {

    public static void main(String[] args) {
        // 🔁 Forcer la base principale
        DatabaseConfig.setUseTestDatabase(false);

        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Connexion échouée à la base de données.");
                return;
            }

            System.out.println("✅ Connexion à la base MySQL [tri_selectif] réussie !");
            System.out.println("📊 Affichage du contenu des tables :");

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
