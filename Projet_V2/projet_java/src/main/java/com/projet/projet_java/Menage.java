package com.projet.projet_java;

import java.util.ArrayList;
import java.util.List;

// Classe Menage
public class Menage {
    private final int idMenage;
    private String nom;
    private final String adresse;
    private int pointsFidelity;
    private final String email;
    private final String motDePasse;
    private final String badgeAccess;
    private final List<HistoriqueDepot> historiqueDepot;

    public Menage(int idMenage, String nom, String adresse, String email, String motDePasse, String badgeAccess) {
        this.idMenage = idMenage;
        this.nom = nom;
        this.adresse = adresse;
        this.email = email;
        this.motDePasse = motDePasse;
        this.badgeAccess = badgeAccess;
        this.pointsFidelity = 0;
        this.historiqueDepot = new ArrayList<>();
    }

    public void ajouterDepot(HistoriqueDepot depot) {
        this.historiqueDepot.add(depot);
        this.pointsFidelity += depot.getPointsGagnes();
    }

    public void modifierPointsFidelity(int points) {
        this.pointsFidelity += points;
    }

    public int getIdMenage() {
        return idMenage;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public int getPointsFidelity() {
        return pointsFidelity;
    }

    public String getBadgeAccess() {
        return badgeAccess;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public List<HistoriqueDepot> getHistoriqueDepot() {
        return new ArrayList<>(historiqueDepot); // Retourne une copie pour éviter la modification externe
    }
}