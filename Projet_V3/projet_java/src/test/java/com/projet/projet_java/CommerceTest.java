package com.projet.projet_java;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CommerceTest {

    @BeforeAll
    public static void setup() {
        DatabaseConfig.setUseTestDatabase(true);
    }

    @Test
    public void testInsertionCommerce() {
        Commerce commerce = new Commerce(0, "EcoShop");

        try (Connection conn = DatabaseConnection.getConnection()) {
            assertNotNull(conn);

            String sql = "INSERT INTO Commerce (nom) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, commerce.getNom());
                int rows = pstmt.executeUpdate();
                assertEquals(1, rows);
                System.out.println("✅ Commerce inséré !");
            }

        } catch (SQLException e) {
            fail("❌ Erreur SQL : " + e.getMessage());
        }
    }
}
