package com.projet.projet_java;

public class PoubelleIntelligente {
    private int idPoubelle;
    private String type;
    private double capaciteMaximale;
    private double capaciteActuelle;
    private String emplacement;

    public PoubelleIntelligente(int idPoubelle, String type, double capaciteMaximale) {
        this.idPoubelle = idPoubelle;
        this.type = type;
        this.capaciteMaximale = capaciteMaximale;
        this.capaciteActuelle = 0;
        this.emplacement = "";
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
        utilisateur.ajouterDepot(new HistoriqueDepot(0, utilisateur.getIdMenage(), this.idPoubelle, "", quantite, points));
    }

    public void envoyerNotification(Menage utilisateur) {
        System.out.println("Notification envoyée à " + utilisateur.getEmail() +
                ": Vous avez gagné " + utilisateur.getPointsFidelity() + " points de fidélité!");
    }


    public int getIdPoubelle() {
        return idPoubelle;
    }

    public String getType() {
        return type;
    }

    public double getCapaciteMaximale() {
        return capaciteMaximale;
    }

    public double getCapaciteActuelle() {
        return capaciteActuelle;
    }

    public String getEmplacement() {
        return emplacement;
    }

    
    public void setIdPoubelle(int idPoubelle) {
        this.idPoubelle = idPoubelle;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCapaciteMaximale(double capaciteMaximale) {
        this.capaciteMaximale = capaciteMaximale;
    }

    public void setCapaciteActuelle(double capaciteActuelle) {
        this.capaciteActuelle = capaciteActuelle;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }
}

