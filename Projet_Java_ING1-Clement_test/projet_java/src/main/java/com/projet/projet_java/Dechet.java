package com.projet.projet_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class Dechet {
    private int idDechet;
    private String typeDechet;
    private double poids;

    public Dechet(int idDechet, String typeDechet, double poids) {
        this.idDechet = idDechet;
        this.typeDechet = typeDechet;
        this.poids = poids;
    }


    public int getIdDechet() {
        return idDechet;
    }

    public String getTypeDechet() {
        return typeDechet;
    }

    public double getPoids() {
        return poids;
    }


    public void setIdDechet(int idDechet) {
        this.idDechet = idDechet;
    }

    public void setTypeDechet(String typeDechet) {
        this.typeDechet = typeDechet;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }
}

