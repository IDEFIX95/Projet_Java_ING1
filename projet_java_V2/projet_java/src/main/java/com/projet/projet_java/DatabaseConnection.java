package com.projet.projet_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/tri_selectif"; // Base de données tri_selectif
    private static final String USER = "root"; // Change avec ton utilisateur MySQL
    private static final String PASSWORD = ""; // Mets ton mot de passe MySQL

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connexion réussie à la base de données !");
        } catch (SQLException e) {
            System.out.println("❌ Erreur de connexion : " + e.getMessage());
        }
        return conn;
    }
}
