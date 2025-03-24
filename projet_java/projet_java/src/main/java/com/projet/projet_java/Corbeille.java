package com.projet.projet_java;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Corbeille {
    private int idCorbeille;
    private List<Dechet> listeDechets;

    public Corbeille(int idCorbeille) {
        this.idCorbeille = idCorbeille;
        this.listeDechets = new ArrayList<>();
    }

    public void ajouterDechet(Dechet dechet) {
        this.listeDechets.add(dechet);
    }

    public double calculerPoidsTotal() {
        return listeDechets.stream().mapToDouble(Dechet::getPoids).sum();
    }
}
