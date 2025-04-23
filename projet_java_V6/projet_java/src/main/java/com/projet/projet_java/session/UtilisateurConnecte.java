package com.projet.projet_java.session;

import com.projet.projet_java.model.Menage;

public class UtilisateurConnecte {
    private static UtilisateurConnecte instance;
    private Menage menage;

    private UtilisateurConnecte() {}

    public static UtilisateurConnecte getInstance() {
        if (instance == null) {
            instance = new UtilisateurConnecte();
        }
        return instance;
    }

    public Menage getMenage() {
        return menage;
    }

    public void setMenage(Menage menage) {
        this.menage = menage;
    }

    public void seDeconnecter() {
        menage = null;
    }

    public boolean estConnecte() {
        return menage != null;
    }

    // ✅ Nouvelle méthode pour que ton controller compile
    public void clear() {
        seDeconnecter();
    }
}
