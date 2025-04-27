package com.projet.projet_java;

import com.projet.projet_java.model.Commerce;
import com.projet.projet_java.utils.ConnexionBDD;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitaire pour la classe Commerce.
 */
public class CommerceTest {

    @Test
    public void testInsertionCommerce() {
        Commerce commerce = new Commerce(0, "Test Commerce", "123 Rue du Test");

        try (Connection conn = ConnexionBDD.getConnection()) {
            assertNotNull(conn, "❌ Connexion à la base échouée.");

            String sql = "INSERT INTO Commerce (nom, adresse) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, commerce.getNom());
                pstmt.setString(2, commerce.getAdresse());
                int result = pstmt.executeUpdate();
                assertEquals(1, result, "❌ L'insertion du commerce a échoué.");
                System.out.println("✅ Commerce inséré avec succès !");
            }

        } catch (SQLException e) {
            fail("❌ Erreur SQL lors de l'insertion : " + e.getMessage());
        }
    }
}
