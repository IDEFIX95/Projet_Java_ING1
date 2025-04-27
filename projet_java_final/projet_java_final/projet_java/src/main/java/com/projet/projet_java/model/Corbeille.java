// Classe représentant une corbeille contenant plusieurs déchets.
package com.projet.projet_java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Modèle représentant une Corbeille temporaire
 * où sont stockés plusieurs déchets avant d'être déposés dans une poubelle.
 */
public class Corbeille {

    private int idCorbeille;
    private List<Dechet> listeDechets;

    /**
     * Constructeur principal de la corbeille.
     * Initialise une nouvelle liste vide de déchets.
     *
     * @param idCorbeille L'identifiant unique de la corbeille.
     */
    public Corbeille(int idCorbeille) {
        this.idCorbeille = idCorbeille;
        this.listeDechets = new ArrayList<>();
    }

    /**
     * Ajoute un déchet à la corbeille.
     *
     * @param dechet Le déchet à ajouter.
     */
    public void ajouterDechet(Dechet dechet) {
        this.listeDechets.add(dechet);
    }

    /**
     * Calcule et retourne le poids total de tous les déchets présents dans la corbeille.
     *
     * @return Poids total en kilogrammes.
     */
    public double calculerPoidsTotal() {
        return listeDechets.stream()
                .mapToDouble(Dechet::getPoids)
                .sum();
    }

    // -------------------------------
    // Getters
    // -------------------------------

    public int getIdCorbeille() {
        return idCorbeille;
    }

    public List<Dechet> getListeDechets() {
        return listeDechets;
    }

    // -------------------------------
    // Setters
    // -------------------------------

    public void setIdCorbeille(int idCorbeille) {
        this.idCorbeille = idCorbeille;
    }

    public void setListeDechets(List<Dechet> listeDechets) {
        this.listeDechets = listeDechets;
    }
}
