package com.projet.projet_java.model;

import com.projet.projet_java.model.TypeDechet;

public enum TypePoubelle {
    VERTE, JAUNE, BLEUE, CLASSIQUE;

    public boolean estCompatible(TypeDechet type) {
        return switch (this) {
            case VERTE -> type == TypeDechet.VERRE;
            case JAUNE -> type == TypeDechet.PLASTIQUE || type == TypeDechet.CARTON || type == TypeDechet.METAL;
            case BLEUE -> type == TypeDechet.CARTON;
            case CLASSIQUE -> true;
        };
    }
}
