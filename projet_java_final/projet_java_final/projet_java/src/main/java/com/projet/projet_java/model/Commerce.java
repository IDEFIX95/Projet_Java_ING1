package com.projet.projet_java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modèle représentant un commerce partenaire.
 * Un commerce propose des produits et est associé à une adresse.
 */
public class Commerce {

    private int idCommerce;
    private String nom;
    private String adresse;
    private List<String> produitsDisponibles;

    /**
     * Constructeur principal pour créer un commerce.
     * Initialise la liste de produits disponible comme vide.
     *
     * @param idCommerce Identifiant unique du commerce.
     * @param nom Nom du commerce.
     * @param adresse Adresse physique du commerce.
     */
    public Commerce(int idCommerce, String nom, String adresse) {
        this.idCommerce = idCommerce;
        this.nom = nom;
        this.adresse = adresse;
        this.produitsDisponibles = new ArrayList<>();
    }

    // -------------------------------
    // Getters
    // -------------------------------

    public int getIdCommerce() {
        return idCommerce;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<String> getProduitsDisponibles() {
        return produitsDisponibles;
    }

    // -------------------------------
    // Setters
    // -------------------------------

    public void setIdCommerce(int idCommerce) {
        this.idCommerce = idCommerce;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setProduitsDisponibles(List<String> produitsDisponibles) {
        this.produitsDisponibles = produitsDisponibles;
    }

    /**
     * Ajoute un produit disponible à la liste du commerce.
     *
     * @param produit Le produit à ajouter.
     */
    public void ajouterProduit(String produit) {
        this.produitsDisponibles.add(produit);
    }

    @Override
    public String toString() {
        return "Commerce{" +
                "id=" + idCommerce +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", produits=" + produitsDisponibles +
                '}';
    }
}
