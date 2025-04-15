package com.projet.projet_java;

public class Waste {
    private final int idWaste;
    private String typeWaste;
    private double poids;

    public Waste(int idWaste, String typeWaste, double poids) {
        this.idWaste = idWaste;
        this.typeWaste = typeWaste;
        this.poids = poids;
    }

    public double getPoids() {
        return poids;
    }

    public double setPoids() {
        return poids;
    }

    public String gettypeWaste() {
        return typeWaste;
    }

    public String settypeWaste() {
        return typeWaste;
    }

    public int getidWaste() {
        return idWaste;
    }


}

