package com.projet.projet_java.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe utilitaire pour le hachage des mots de passe.
 * Utilise l'algorithme SHA-256.
 */
public class HashUtils {

    /**
     * 🔹 Méthode pour hacher un mot de passe en SHA-256.
     * @param password Le mot de passe en clair à hacher
     * @return Le mot de passe haché en format hexadécimal
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());

            // ➔ Convertir le tableau d'octets en une chaîne hexadécimale
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("❌ Erreur de hachage du mot de passe", e);
        }
    }
}
