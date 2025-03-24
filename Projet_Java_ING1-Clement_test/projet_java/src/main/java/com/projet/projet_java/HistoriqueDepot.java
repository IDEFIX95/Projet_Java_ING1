package com.projet.projet_java;

public class HistoriqueDepot {
    private int idDepot;
    private int idMenage;
    private int idPoubelle;
    private String heureDepot;
    private double quantiteDechets;
    private int pointsGagnes;

    public HistoriqueDepot(int idDepot, int idMenage, int idPoubelle, String heureDepot, double quantityWastes, int pointsGagnes) {
        this.idDepot = idDepot;
        this.idMenage = idMenage;
        this.idPoubelle = idPoubelle;
        this.heureDepot = heureDepot;
        this.quantiteDechets = quantityWastes;
        this.pointsGagnes = pointsGagnes;
    }


    public int getIdDepot() {
        return idDepot;
    }

    public int getIdMenage() {
        return idMenage;
    }

    public int getIdPoubelle() {
        return idPoubelle;
    }

    public String getHeureDepot() {
        return heureDepot;
    }

    public double getQuantiteDechets() {
        return quantiteDechets;
    }

    public int getPointsGagnes() {
        return pointsGagnes;
    }


    public void setIdDepot(int idDepot) {
        this.idDepot = idDepot;
    }

    public void setIdMenage(int idMenage) {
        this.idMenage = idMenage;
    }

    public void setIdPoubelle(int idPoubelle) {
        this.idPoubelle = idPoubelle;
    }

    public void setHeureDepot(String heureDepot) {
        this.heureDepot = heureDepot;
    }

    public void setQuantiteDechets(double quantiteDechets) {
        this.quantiteDechets = quantiteDechets;
    }

    public void setPointsGagnes(int pointsGagnes) {
        this.pointsGagnes = pointsGagnes;
    }
}



