package com.projet.projet_java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MenageTest {

    @BeforeAll
    public static void setUp() {
        DatabaseConfig.setUseTestDatabase(true);
    }

    @Test
    public void testInsertionMenage() {
        String emailUnique = "testeur+" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";

        try (Connection conn = DatabaseConnection.getConnection()) {
            assertNotNull(conn, "❌ Connexion échouée");

            String sql = "INSERT INTO Menage (nom, adresse, email, motDePasse, badgeAccess, pointsFidelity) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "Testeur");
                pstmt.setString(2, "42 rue du Test");
                pstmt.setString(3, emailUnique);
                pstmt.setString(4, "motdepasse");
                pstmt.setString(5, "BADGE123");
                pstmt.setInt(6, 50);

                int result = pstmt.executeUpdate();
                assertEquals(1, result, "❌ Insertion du ménage échouée !");
                System.out.println("✅ Menage inséré avec succès !");
            }

        } catch (SQLException e) {
            fail("❌ Erreur SQL : " + e.getMessage());
        }
    }
}
