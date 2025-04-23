package com.projet.projet_java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class CategorieTest {

    @BeforeAll
    public static void setup() {
        DatabaseConfig.setUseTestDatabase(true);
    }

    @Test
    public void testInsertionCategorie() {
        Categorie cat = new Categorie(0, "Plastique");

        try (Connection conn = DatabaseConnection.getConnection()) {
            assertNotNull(conn);

            String sql = "INSERT INTO Categorie (listeCategorie) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cat.getListeCategorie());
                int rows = pstmt.executeUpdate();
                assertEquals(1, rows);
                System.out.println("✅ Categorie insérée !");
            }

        } catch (SQLException e) {
            fail("❌ Erreur SQL : " + e.getMessage());
        }
    }
}
