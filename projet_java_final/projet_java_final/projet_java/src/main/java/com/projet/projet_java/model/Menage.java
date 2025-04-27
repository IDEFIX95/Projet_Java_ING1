package com.projet.projet_java.model;

/**
 * Classe représentant un ménage (utilisateur final de l'application).
 * Chaque ménage possède un compte avec un nom, une adresse, un email,
 * un mot de passe (haché), un badge d'accès et un compteur de points de fidélité.
 */
public class Menage {
    private int idMenage;
    private String nom;
    private String adresse;
    private String email;
    private String motDePasse;
    private String badgeAccess;
    private int pointsFidelity;

    //  Constructeur principal complet
    public Menage(int idMenage, String nom, String adresse, String email, String motDePasse, String badgeAccess, int pointsFidelity) {
        this.idMenage = idMenage;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.motDePasse = motDePasse;
        this.badgeAccess = badgeAccess;
        this.pointsFidelity = pointsFidelity;
    }

    // -------------------------------
    // Getters
    // -------------------------------

    public int getIdMenage() {
        return idMenage;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getBadgeAccess() {
        return badgeAccess;
    }

    public int getPointsFidelity() {
        return pointsFidelity;
    }

    // -------------------------------
    // Setters
    // -------------------------------

    public void setIdMenage(int idMenage) {
        this.idMenage = idMenage;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setBadgeAccess(String badgeAccess) {
        this.badgeAccess = badgeAccess;
    }

    public void setPointsFidelity(int pointsFidelity) {
        this.pointsFidelity = pointsFidelity;
    }

    // -------------------------------
    // Méthode utilitaire
    // -------------------------------

    /**
     * Ajoute ou enlève des points au compteur de fidélité du ménage.
     * @param pointsAjoutes Le nombre de points à ajouter (ou à enlever si négatif).
     */
    public void modifierPointsFidelity(int pointsAjoutes) {
        this.pointsFidelity += pointsAjoutes;
    }
}
