
package com.projet.projet_java;

public class BonAchat {
    private int idBonAchat;
    private int nombreDeBon;

    public BonAchat(int idBonAchat, int nombreDeBon) {
        this.idBonAchat = idBonAchat;
        this.nombreDeBon = nombreDeBon;
    }

    public int getIdBonAchat() {
        return idBonAchat;
    }

    public void setIdBonAchat(int idBonAchat) {
        this.idBonAchat = idBonAchat;
    }

    public int getNombreDeBon() {
        return nombreDeBon;
    }

    public void setNombreDeBon(int nombreDeBon) {
        this.nombreDeBon = nombreDeBon;
    }
}
