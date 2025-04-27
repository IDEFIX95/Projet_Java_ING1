package com.projet.projet_java.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire pour gérer la connexion à la base de données MySQL.
 */
public class ConnexionBDD {

    // URL de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/tri_selectif";

    // Identifiants de connexion
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Modifier ici si mot de passe nécessaire

    /**
     * Retourne une connexion active à la base de données.
     * @return Connection objet pour interagir avec la BDD
     * @throws SQLException si une erreur de connexion survient
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
