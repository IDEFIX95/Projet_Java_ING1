package com.projet.projet_java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class PoubelleIntelligenteTest {

    @BeforeAll
    public static void setup() {
        DatabaseConfig.setUseTestDatabase(true);
    }

    @Test
    public void testInsertionPoubelle() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            assertNotNull(conn);

            String sql = "INSERT INTO PoubelleIntelligente (type, capaciteMaximale, emplacement) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, "Plastique");
                pstmt.setDouble(2, 100.0);
                pstmt.setString(3, "Quartier Test");
                int rows = pstmt.executeUpdate();
                assertEquals(1, rows);
                System.out.println("✅ Poubelle insérée !");
            }

        } catch (SQLException e) {
            fail("❌ Erreur SQL : " + e.getMessage());
        }
    }
}

