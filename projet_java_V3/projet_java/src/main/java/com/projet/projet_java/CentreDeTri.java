package com.projet.projet_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class CentreDeTri {
    private int idCentre;
    private String nom;
    private String adresse;
    private List<PoubelleIntelligente> listePoubelles;

    public CentreDeTri(int idCentre, String nom, String adresse) {
        this.idCentre = idCentre;
        this.nom = nom;
        this.adresse = adresse;
        this.listePoubelles = new ArrayList<>();
    }


    public int getIdCentre() {
        return idCentre;
    }

    public void setIdCentre(int idCentre) {
        this.idCentre = idCentre;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public List<PoubelleIntelligente> getListePoubelles() {
        return listePoubelles;
    }

    public void setListePoubelles(List<PoubelleIntelligente> listePoubelles) {
        this.listePoubelles = listePoubelles;
    }


    public void ajouterPoubelle(PoubelleIntelligente poubelle) {
        this.listePoubelles.add(poubelle);
    }
}

