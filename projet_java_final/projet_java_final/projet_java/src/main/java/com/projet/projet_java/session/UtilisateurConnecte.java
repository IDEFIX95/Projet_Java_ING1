package com.projet.projet_java.session;

import com.projet.projet_java.controller.DashboardController;
import com.projet.projet_java.model.Menage;

/**
 * Classe UtilisateurConnecte gérant l'utilisateur actuellement connecté.
 * Implémente le patron Singleton pour assurer une seule instance partagée.
 */
public class UtilisateurConnecte {

    private static UtilisateurConnecte instance;
    private Menage menage;
    private DashboardController dashboardController;

    /**
     * Constructeur privé pour forcer l'utilisation du Singleton.
     */
    private UtilisateurConnecte() {}

    /**
     * Retourne l'unique instance de UtilisateurConnecte (créée si nécessaire).
     * @return l'instance UtilisateurConnecte
     */
    public static UtilisateurConnecte getInstance() {
        if (instance == null) {
            instance = new UtilisateurConnecte();
        }
        return instance;
    }

    /**
     * Retourne le ménage connecté.
     * @return l'objet Menage actuellement connecté
     */
    public Menage getMenage() {
        return menage;
    }

    /**
     * Définit le ménage connecté.
     * @param menage Ménage à connecter
     */
    public void setMenage(Menage menage) {
        this.menage = menage;
    }

    /**
     * Vérifie si un ménage est connecté.
     * @return true si connecté, sinon false
     */
    public boolean estConnecte() {
        return menage != null;
    }

    /**
     * Déconnecte l'utilisateur (réinitialise ménage et dashboard).
     */
    public void seDeconnecter() {
        this.menage = null;
        this.dashboardController = null;
    }

    /**
     * Méthode utilitaire pour forcer une déconnexion (identique à seDeconnecter).
     */
    public void clear() {
        seDeconnecter();
    }

    /**
     * Retourne le contrôleur associé au Dashboard.
     * @return DashboardController courant
     */
    public DashboardController getDashboardController() {
        return dashboardController;
    }

    /**
     * Définit le contrôleur du Dashboard.
     * @param dashboardController contrôleur à associer
     */
    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
