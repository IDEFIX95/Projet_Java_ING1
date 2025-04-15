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


    public int getIdCorbeille() {
        return idCorbeille;
    }

    public List<Dechet> getListeDechets() {
        return listeDechets;
    }


    public void setIdCorbeille(int idCorbeille) {
        this.idCorbeille = idCorbeille;
    }

    public void setListeDechets(List<Dechet> listeDechets) {
        this.listeDechets = listeDechets;
    }
}
