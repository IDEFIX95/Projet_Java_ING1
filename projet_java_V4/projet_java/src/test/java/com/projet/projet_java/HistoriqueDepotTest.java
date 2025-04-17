package com.projet.projet_java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class HistoriqueDepotTest {

    @BeforeAll
    public static void setup() {
        DatabaseConfig.setUseTestDatabase(true);
    }

    @Test
    public void testInsertionDepot() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            assertNotNull(conn);

            String sql = "INSERT INTO HistoriqueDepot (idMenage, idPoubelle, quantiteDechets, pointsGagnes) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, 1); // suppose des IDs existants
                pstmt.setInt(2, 1);
                pstmt.setDouble(3, 2.0);
                pstmt.setInt(4, 20);
                int rows = pstmt.executeUpdate();
                assertEquals(1, rows);
                System.out.println("✅ HistoriqueDepot inséré !");
            }

        } catch (SQLException e) {
            fail("❌ Erreur SQL : " + e.getMessage());
        }
    }
}

