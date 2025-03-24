package com.projet.projet_java;


/*import org.junit.platform.suite.api.SelectPackages;*/
//import org.junit.platform.suite.api.Suite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//@Suite
//@SelectPackages("com.projet.projet_java") // Chemin du package contenant les tests
public class MainTest {
    public static void main(String[] args) {
        // Vérifier la connexion à la base de données
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ La connexion à la base de données a échoué. Vérifiez DatabaseConnection.java.");
                return; // Arrêter l'exécution si la connexion a échoué
            }

            System.out.println("✅ Connexion à la base MySQL réussie !");

            // Afficher les déchets existants
            String query = "SELECT * FROM Waste";
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                System.out.println("📌 Contenu de la table Waste :");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") +
                            ", Type: " + rs.getString("type") +
                            ", Couleur: " + rs.getString("couleur") +
                            ", Poids: " + rs.getFloat("poids"));
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Erreur SQL : " + e.getMessage());
        }
    }
}

