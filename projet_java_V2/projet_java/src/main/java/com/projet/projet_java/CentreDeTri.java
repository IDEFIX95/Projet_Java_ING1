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
}
