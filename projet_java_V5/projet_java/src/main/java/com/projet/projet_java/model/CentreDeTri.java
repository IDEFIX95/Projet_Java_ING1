package com.projet.projet_java.model;

import java.util.ArrayList;
import java.util.List;

public class CentreDeTri {

    private int idCentre;
    private String nom;
    private String adresse;
    private List<PoubelleIntelligente> listePoubelles;

    // 🔹 Constructeur principal
    public CentreDeTri(int idCentre, String nom, String adresse) {
        this.idCentre = idCentre;
        this.nom = nom;
        this.adresse = adresse;
        this.listePoubelles = new ArrayList<>();
    }

    // ✅ Getters
    public int getIdCentre() {
        return idCentre;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<PoubelleIntelligente> getListePoubelles() {
        return listePoubelles;
    }

    // ✅ Setters
    public void setIdCentre(int idCentre) {
        this.idCentre = idCentre;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setListePoubelles(List<PoubelleIntelligente> listePoubelles) {
        this.listePoubelles = listePoubelles;
    }

    // ➕ Méthode utilitaire
    public void ajouterPoubelle(PoubelleIntelligente poubelle) {
        this.listePoubelles.add(poubelle);
    }
}
