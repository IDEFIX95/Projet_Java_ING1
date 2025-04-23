package com.projet.projet_java.model;

import java.util.ArrayList;
import java.util.List;

public class Commerce {
    private int idCommerce;
    private String nom;
    private String adresse;
    private List<String> produitsDisponibles;

    // 🔹 Constructeur principal
    public Commerce(int idCommerce, String nom, String adresse) {
        this.idCommerce = idCommerce;
        this.nom = nom;
        this.adresse = adresse;
        this.produitsDisponibles = new ArrayList<>();
    }

    // ✅ Getters
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

    // ✅ Setters
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

    // 🔸 Ajouter un produit à la liste
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
