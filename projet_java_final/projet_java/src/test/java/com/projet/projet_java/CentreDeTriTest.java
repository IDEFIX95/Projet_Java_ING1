package com.projet.projet_java;

public class CentreDeTriTest {
    private int idCentre;
    private String nom;
    private String adresse;

    public CentreDeTriTest(int idCentre, String nom, String adresse) {
        this.idCentre = idCentre;
        this.nom = nom;
        this.adresse = adresse;
    }

    public int getIdCentre() {
        return idCentre;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return nom + " - " + adresse;
    }
}
