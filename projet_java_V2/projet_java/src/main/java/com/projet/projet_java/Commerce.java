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
}
