package com.projet.projet_java.model;

import java.time.LocalDateTime;

/**
 * Classe représentant un historique de dépôt de déchets.
 * Un dépôt est lié à un ménage, une poubelle, une date/heure précise,
 * une quantité de déchets déposée et des points fidélité gagnés.
 */
public class HistoriqueDepot {

    private int idDepot;
    private int idMenage;
    private int idPoubelle;
    private LocalDateTime heureDepot;
    private double quantiteDechets;
    private int pointsGagnes;

    //  Constructeur complet (utile pour récupérer depuis la BDD)
    public HistoriqueDepot(int idDepot, int idMenage, int idPoubelle, LocalDateTime heureDepot, double quantiteDechets, int pointsGagnes) {
        this.idDepot = idDepot;
        this.idMenage = idMenage;
        this.idPoubelle = idPoubelle;
        this.heureDepot = heureDepot;
        this.quantiteDechets = quantiteDechets;
        this.pointsGagnes = pointsGagnes;
    }

    //  Constructeur pour insertion rapide (heure générée automatiquement)
    public HistoriqueDepot(int idMenage, int idPoubelle, double quantiteDechets, int pointsGagnes) {
        this.idMenage = idMenage;
        this.idPoubelle = idPoubelle;
        this.heureDepot = LocalDateTime.now();
        this.quantiteDechets = quantiteDechets;
        this.pointsGagnes = pointsGagnes;
    }

    // -------------------------------
    // Getters
    // -------------------------------

    public int getIdDepot() {
        return idDepot;
    }

    public int getIdMenage() {
        return idMenage;
    }

    public int getIdPoubelle() {
        return idPoubelle;
    }

    public LocalDateTime getHeureDepot() {
        return heureDepot;
    }

    public double getQuantiteDechets() {
        return quantiteDechets;
    }

    public int getPointsGagnes() {
        return pointsGagnes;
    }

    // -------------------------------
    // Setters
    // -------------------------------

    public void setIdDepot(int idDepot) {
        this.idDepot = idDepot;
    }

    public void setHeureDepot(LocalDateTime heureDepot) {
        this.heureDepot = heureDepot;
    }

    // -------------------------------
    // Méthodes Utilitaires
    // -------------------------------

    /**
     * Méthode temporaire pour indiquer un type de déchet lié au dépôt.
     */
    public String getTypeDechetPrincipal() {
        return "déchets"; // Simplifié ici
    }

    @Override
    public String toString() {
        return "HistoriqueDepot{" +
                "idDepot=" + idDepot +
                ", idMenage=" + idMenage +
                ", idPoubelle=" + idPoubelle +
                ", heureDepot=" + heureDepot +
                ", quantiteDechets=" + quantiteDechets +
                ", pointsGagnes=" + pointsGagnes +
                '}';
    }
}
