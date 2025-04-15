package com.projet.projet_java;

import java.util.ArrayList;
import java.util.List;

public class Corbeille {
    private int idCorbeille;
    private List<Waste> listeDechets;

    public Corbeille(int idCorbeille) {
        this.idCorbeille = idCorbeille;
        this.listeDechets = new ArrayList<>();
    }

    public void ajouterDechet(Waste waste) {
        this.listeDechets.add(waste);
    }

    public double calculerPoidsTotal() {
        return listeDechets.stream().mapToDouble(Waste::getPoids).sum();
    }
}
