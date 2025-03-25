package com.projet.projet_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class Commerce {
    private int idCommerce;
    private String nom;
    private List<String> listeProduitsDisponibles;

    public Commerce(int idCommerce, String nom) {
        this.idCommerce = idCommerce;
        this.nom = nom;
        this.listeProduitsDisponibles = new ArrayList<>();
    }


    public int getIdCommerce() {
        return idCommerce;
    }

    public String getNom() {
        return nom;
    }

    public List<String> getListeProduitsDisponibles() {
        return listeProduitsDisponibles;
    }


    public void setIdCommerce(int idCommerce) {
        this.idCommerce = idCommerce;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setListeProduitsDisponibles(List<String> listeProduitsDisponibles) {
        this.listeProduitsDisponibles = listeProduitsDisponibles;
    }
}

