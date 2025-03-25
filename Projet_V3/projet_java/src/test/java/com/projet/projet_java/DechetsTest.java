package com.projet.projet_java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour l'entité Dechet, avec insertion en base de test.
 */
public class DechetsTest {

    @BeforeAll
    public static void setUp() {
        // Utiliser la base de test avant toute connexion
        DatabaseConfig.setUseTestDatabase(true);
    }

    @Test
    public void testInsertDechetDansBDD() {
        Dechet dechet = new Dechet(0, "Verre", 2.5); // L'ID est auto-généré par MySQL

        try (Connection conn = DatabaseConnection.getConnection()) {
            assertNotNull(conn, "❌ Connexion à la base échouée");

            String sql = "INSERT INTO Dechet (typeDechet, poids) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, dechet.getTypeDechet());
                pstmt.setDouble(2, dechet.getPoids());
                int result = pstmt.executeUpdate();
                assertEquals(1, result, "❌ Insertion échouée !");
                System.out.println("✅ Dechet inséré avec succès dans la base de test !");
            }

        } catch (SQLException e) {
            fail("❌ Erreur SQL lors de l'insertion : " + e.getMessage());
        }
    }

    @Test
    public void testPoidsDechet() {
        Dechet dechet = new Dechet(5, "Verre", 2.5);
        assertEquals(2.5, dechet.getPoids(), "❌ Mauvais poids récupéré");
    }
}
