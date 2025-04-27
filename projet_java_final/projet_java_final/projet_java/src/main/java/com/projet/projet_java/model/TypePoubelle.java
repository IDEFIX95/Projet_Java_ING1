package com.projet.projet_java.model;

/**
 * Enumération des types de poubelles disponibles,
 * avec une méthode pour vérifier la compatibilité
 * avec les types de déchets.
 */
public enum TypePoubelle {
    VERTE,
    JAUNE,
    BLEUE,
    CLASSIQUE;

    /**
     * Vérifie si un type de déchet donné est accepté par cette poubelle.
     *
     * @param type Le type de déchet à vérifier
     * @return true si compatible, false sinon
     */
    public boolean estCompatible(TypeDechet type) {
        return switch (this) {
            case VERTE -> type == TypeDechet.VERRE;
            case JAUNE -> type == TypeDechet.PLASTIQUE || type == TypeDechet.CARTON || type == TypeDechet.METAL;
            case BLEUE -> type == TypeDechet.CARTON;
            case CLASSIQUE -> true; // Accepte tous les déchets par défaut
        };
    }
}
