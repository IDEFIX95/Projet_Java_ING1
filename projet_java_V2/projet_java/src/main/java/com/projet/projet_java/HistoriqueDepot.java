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

    public int getPointsGagnes() {
        return pointsGagnes;
    }
}
