package com.projet.projet_java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modèle représentant un Centre de Tri.
 * Un centre de tri possède un nom, une adresse, et contient plusieurs poubelles intelligentes.
 */
public class CentreDeTri {

    private int idCentre;
    private String nom;
    private String adresse;
    private List<PoubelleIntelligente> listePoubelles;

    /**
     * Constructeur principal pour créer un centre de tri.
     * Initialise la liste de poubelles vide.
     *
     * @param idCentre Identifiant du centre (généré par la BDD).
     * @param nom Nom du centre de tri.
     * @param adresse Adresse du centre.
     */
    public CentreDeTri(int idCentre, String nom, String adresse) {
        this.idCentre = idCentre;
        this.nom = nom;
        this.adresse = adresse;
        this.listePoubelles = new ArrayList<>();
    }

    // -------------------------------
    // Getters
    // -------------------------------

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

    // -------------------------------
    // Setters
    // -------------------------------

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

    /**
     * Ajoute une nouvelle poubelle intelligente au centre de tri.
     * @param poubelle La poubelle intelligente à ajouter.
     */
    public void ajouterPoubelle(PoubelleIntelligente poubelle) {
        this.listePoubelles.add(poubelle);
    }
}
