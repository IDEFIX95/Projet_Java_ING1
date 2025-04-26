package com.projet.projet_java.model;

import java.time.LocalDate;

public class BonAchat {
    private int idBonAchat;
    private int idMenage;
    private int idCommerce;
    private int pointsUtilises;
    private LocalDate dateCreation;
    private LocalDate dateExpiration;
    private String categorie;

    // ✅ Nouveau champ ajouté pour l'affichage !
    private String nomCommerce; // pour afficher directement dans la TableView

    // 🔹 Constructeur principal complet
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

    // 🔹 Constructeur pour insertion (sans id, date auto, expiration +30j)
    public BonAchat(int idMenage, int idCommerce, int pointsUtilises, String categorie) {
        this(0, idMenage, idCommerce, pointsUtilises, LocalDate.now(), LocalDate.now().plusDays(30), categorie);
    }

    // ✅ Getters
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

    // ✅ Setters
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

    // ✅ Utilitaire : savoir si le bon est encore valide
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
