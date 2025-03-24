package com.projet.projet_java;

public class PoubelleIntelligente {
    private int idPoubelle;
    private String type;
    private double capaciteMaximale;
    private double capaciteActuelle;

    public PoubelleIntelligente(int idPoubelle, String type, double capacityMaximale) {
        this.idPoubelle = idPoubelle;
        this.type = type;
        this.capaciteMaximale = capacityMaximale;
        this.capaciteActuelle = 0;
    }

    public boolean estPlein() {
        return capaciteActuelle >= capaciteMaximale;
    }

    public boolean identifierUtilisateur(Menage utilisateur) {
        return utilisateur.getBadgeAccess() != null;
    }

    public double calculerQuantityWastes(Corbeille corbeille) {
        return corbeille.calculerPoidsTotal();
    }

    public void attribuerPointsFidelity(Menage utilisateur, double quantite) {
        int points = (int) quantite * 10; // Exemple de conversion
        utilisateur.ajouterDepot(new HistoriqueDepot(0, utilisateur.getPointsFidelity(), this.idPoubelle, "", quantite, points));
    }

    public void envoyerNotification(Menage utilisateur) {
        System.out.println("Notification envoyée à " + utilisateur.email + ": Vous avez gagné " + utilisateur.getPointsFidelity() + " points de fidélité!");
    }
}

