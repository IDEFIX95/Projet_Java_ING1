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

    public double getPoids() {
        return poids;
    }
}
