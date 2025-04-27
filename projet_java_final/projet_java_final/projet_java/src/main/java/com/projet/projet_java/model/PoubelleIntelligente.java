package com.projet.projet_java.model;

/**
 * Classe représentant une poubelle intelligente dans un centre de tri.
 * Une poubelle a un type (plastique, verre...), une capacité maximale, un emplacement, et appartient à un centre.
 */
public class PoubelleIntelligente {
    private int idPoubelle;
    private TypePoubelle typePoubelle;
    private double capaciteMaximale;
    private double capaciteActuelle;
    private String emplacement;
    private int idCentre;

    // 🔹 Constructeur complet (utile lors de la récupération depuis la base de données)
    public PoubelleIntelligente(int idPoubelle, TypePoubelle typePoubelle, double capaciteMaximale,
                                double capaciteActuelle, String emplacement, int idCentre) {
        this.idPoubelle = idPoubelle;
        this.typePoubelle = typePoubelle;
        this.capaciteMaximale = capaciteMaximale;
        this.capaciteActuelle = capaciteActuelle;
        this.emplacement = emplacement;
        this.idCentre = idCentre;
    }

    // 🔹 Constructeur simplifié (ex: création initiale sans affectation complète)
    public PoubelleIntelligente(int idPoubelle, TypePoubelle typePoubelle, double capaciteMaximale) {
        this(idPoubelle, typePoubelle, capaciteMaximale, 0.0, "", 0);
    }

    // -------------------------------
    // Méthodes métier
    // -------------------------------

    /**
     * Vérifie si la poubelle est pleine.
     */
    public boolean estPlein() {
        return capaciteActuelle >= capaciteMaximale;
    }

    /**
     * Vérifie si un déchet donné est compatible avec cette poubelle.
     */
    public boolean verifierCompatibilite(Dechet d) {
        return typePoubelle.estCompatible(d.getTypeDechet());
    }

    /**
     * Calcule la quantité totale de déchets dans une corbeille.
     */
    public double calculerQuantityWastes(Corbeille corbeille) {
        return corbeille.calculerPoidsTotal();
    }

    /**
     * Envoie une notification au ménage (simple affichage console ici).
     */
    public void envoyerNotification(Menage utilisateur) {
        System.out.println("Notification envoyée à " + utilisateur.getEmail()
                + " : Vous avez maintenant " + utilisateur.getPointsFidelity() + " points !");
    }

    // -------------------------------
    // Getters
    // -------------------------------

    public int getIdPoubelle() {
        return idPoubelle;
    }

    public TypePoubelle getTypePoubelle() {
        return typePoubelle;
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

    public int getIdCentre() {
        return idCentre;
    }

    // -------------------------------
    // Setters
    // -------------------------------

    public void setIdPoubelle(int idPoubelle) {
        this.idPoubelle = idPoubelle;
    }

    public void setTypePoubelle(TypePoubelle typePoubelle) {
        this.typePoubelle = typePoubelle;
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

    public void setIdCentre(int idCentre) {
        this.idCentre = idCentre;
    }

    // -------------------------------
    // ToString (utile pour ComboBox)
    // -------------------------------

    @Override
    public String toString() {
        return typePoubelle + " - " + emplacement;
    }
}
