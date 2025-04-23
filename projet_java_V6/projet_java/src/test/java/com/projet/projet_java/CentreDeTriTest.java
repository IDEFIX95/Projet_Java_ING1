package com.projet.projet_java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CentreDeTriTest {

    @BeforeAll
    public static void setup() {
        DatabaseConfig.setUseTestDatabase(true);
    }

    @Test
    public void testInsertionCentre() {
        CentreDeTri centre = new CentreDeTri(0, "Centre Nord", "123 Rue Recycle");

        try (Connection conn = DatabaseConnection.getConnection()) {
            assertNotNull(conn);

            String sql = "INSERT INTO CentreDeTri (nom, adresse) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, centre.getNom());
                pstmt.setString(2, centre.getAdresse());
                int rows = pstmt.executeUpdate();
                assertEquals(1, rows);
                System.out.println("✅ CentreDeTri inséré !");
            }

        } catch (SQLException e) {
            fail("❌ Erreur SQL : " + e.getMessage());
        }
    }
}

