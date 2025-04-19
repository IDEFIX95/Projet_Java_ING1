package com.projet.projet_java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Date;

public class ContratPartenariatTest {

    @BeforeAll
    public static void setup() {
        DatabaseConfig.setUseTestDatabase(true);
    }

    @Test
    public void testInsertionContrat() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            assertNotNull(conn);

            // On suppose que le commerce ID 1 existe
            String sql = "INSERT INTO ContratPartenariat (idCommerce, dateDebut, dateFin) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, 1);
                pstmt.setDate(2, Date.valueOf("2025-01-01"));
                pstmt.setDate(3, Date.valueOf("2025-12-31"));
                int rows = pstmt.executeUpdate();
                assertEquals(1, rows);
                System.out.println("✅ Contrat inséré !");
            }

        } catch (SQLException e) {
            fail("❌ Erreur SQL : " + e.getMessage());
        }
    }
}

