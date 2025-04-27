package com.projet.projet_java.model;

import java.time.LocalDate;

/**
 * Modèle représentant un Bon d'Achat pour un ménage.
 * Un bon est lié à un commerce, a un coût en points, une date de création et une date d'expiration.
 */
public class BonAchat {
    private int idBonAchat;
    private int idMenage;
    private int idCommerce;
    private int pointsUtilises;
    private LocalDate dateCreation;
    private LocalDate dateExpiration;
    private String categorie;

    //  Champ utilisé uniquement pour l'affichage dans les vues (nom du commerce associé)
    private String nomCommerce;

    /**
     * Constructeur complet avec tous les champs.
     */
    public BonAchat(int idBonAchat, int idMenage, int idCommerce, int pointsUtilises,
                    LocalDate dateCreation, LocalDate dateExpiration, String categorie) {
        this.idBonAchat = idBonAchat;
        this.idMenage = idMenage;
        this.idCommerce = idCommerce;
        this.pointsUtilises = pointsUtilises;
        this.dateCreation = dateCreation;
        this.dateExpiration = dateExpiration;
        this.categorie = categorie;
    }

    /**
     * Constructeur simplifié utilisé lors de la création d'un nouveau bon.
     * (La date de création est mise à aujourd'hui et expiration à +30 jours automatiquement)
     */
    public BonAchat(int idMenage, int idCommerce, int pointsUtilises, String categorie) {
        this(0, idMenage, idCommerce, pointsUtilises, LocalDate.now(), LocalDate.now().plusDays(30), categorie);
    }

    // -------------------------------
    // Getters
    // -------------------------------

    public int getIdBonAchat() {
        return idBonAchat;
    }

    public int getIdMenage() {
        return idMenage;
    }

    public int getIdCommerce() {
        return idCommerce;
    }

    public int getPointsUtilises() {
        return pointsUtilises;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getNomCommerce() {
        return nomCommerce;
    }

    // -------------------------------
    // Setters
    // -------------------------------

    public void setIdBonAchat(int idBonAchat) {
        this.idBonAchat = idBonAchat;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setNomCommerce(String nomCommerce) {
        this.nomCommerce = nomCommerce;
    }

    /**
     * Vérifie si le bon est toujours valide (pas expiré).
     * @return true si encore valide, false sinon.
     */
    public boolean estValide() {
        return !LocalDate.now().isAfter(dateExpiration);
    }

    @Override
    public String toString() {
        return "BonAchat{" +
                "idBonAchat=" + idBonAchat +
                ", idMenage=" + idMenage +
                ", idCommerce=" + idCommerce +
                ", pointsUtilises=" + pointsUtilises +
                ", dateCreation=" + dateCreation +
                ", dateExpiration=" + dateExpiration +
                ", categorie='" + categorie + '\'' +
                ", nomCommerce='" + nomCommerce + '\'' +
                '}';
    }
}
