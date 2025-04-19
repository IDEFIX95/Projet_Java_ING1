package com.projet.projet_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
public class Categorie {
    private int idCategorie;
    private String listeCategorie;

    public Categorie(int idCategorie, String listeCategorie) {
        this.idCategorie = idCategorie;
        this.listeCategorie = listeCategorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getListeCategorie() {
        return listeCategorie;
    }

    public void setListeCategorie(String listeCategorie) {
        this.listeCategorie = listeCategorie;
    }
}


