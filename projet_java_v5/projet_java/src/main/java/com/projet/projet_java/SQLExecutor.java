package com.projet.projet_java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SQLExecutor {

    private static final Logger LOGGER = Logger.getLogger(SQLExecutor.class.getName());

    public static void runScript(String resourcePath) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            if (conn == null) {
                LOGGER.warning("❌ Connexion à la base impossible.");
                return;
            }

            // Lecture du fichier SQL depuis les ressources
            InputStream input = SQLExecutor.class.getClassLoader().getResourceAsStream(resourcePath);
            if (input == null) {
                LOGGER.severe("❌ Fichier SQL introuvable dans resources : " + resourcePath);
                return;
            }

            String sql = new BufferedReader(new InputStreamReader(input))
                    .lines()
                    .collect(Collectors.joining("\n"));

            for (String statement : sql.split(";")) {
                String trimmed = statement.trim();
                if (!trimmed.isEmpty()) {
                    try (PreparedStatement pstmt = conn.prepareStatement(trimmed)) {
                        pstmt.execute();
                    }
                }
            }

            LOGGER.info("✅ Script SQL exécuté avec succès depuis : " + resourcePath);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "❌ Erreur lors de l'exécution du script SQL :", e);
        }
    }
}
