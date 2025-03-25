package com.projet.projet_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Fournit une connexion JDBC à la base de données en fonction de DatabaseConfig.
 */
public class DatabaseConnection {

    /**
     * Retourne une connexion active à la base selon le mode défini dans DatabaseConfig.
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    DatabaseConfig.getUrl(),
                    DatabaseConfig.getUser(),
                    DatabaseConfig.getPassword()
            );
        } catch (SQLException e) {
            System.out.println("❌ Erreur de connexion : " + e.getMessage());
            return null;
        }
    }
}
