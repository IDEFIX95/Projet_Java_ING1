package com.projet.projet_java.session;

import com.projet.projet_java.controller.DashboardController;
import com.projet.projet_java.model.Menage;

public class UtilisateurConnecte {

    private static UtilisateurConnecte instance;
    private Menage menage;
    private DashboardController dashboardController;

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

    public boolean estConnecte() {
        return menage != null;
    }

    public void seDeconnecter() {
        this.menage = null;
        this.dashboardController = null;
    }

    public void clear() {
        seDeconnecter();
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }

    public void setDashboardController(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }
}
