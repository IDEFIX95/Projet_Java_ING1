package com.projet.projet_java;

/**
 * Gère dynamiquement la configuration de la base de données (test vs principale).
 */
public class DatabaseConfig {

    // Enumération des modes de connexion possibles
    public enum Mode {
        PRINCIPAL,   // Base réelle (prod)
        TEST         // Base de test
    }

    // Mode actif actuel — modifiable dynamiquement
    public static Mode currentMode = Mode.PRINCIPAL;

    /**
     * Permet d'activer la base de test dynamiquement.
     * @param useTest true pour utiliser tri_selectifTest, false pour tri_selectif
     */
    public static void setUseTestDatabase(boolean useTest) {
        currentMode = useTest ? Mode.TEST : Mode.PRINCIPAL;
    }

    public static String getUrl() {
        return switch (currentMode) {
            case TEST -> "jdbc:mysql://localhost:3306/tri_selectifTest";
            case PRINCIPAL -> "jdbc:mysql://localhost:3306/tri_selectif";
        };
    }

    public static String getUser() {
        return "root"; // Change-le si besoin
    }

    public static String getPassword() {
        return "";     // Mets ton mot de passe ici si nécessaire
    }
}
