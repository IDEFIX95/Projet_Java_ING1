package com.projet.projet_java.model;

import java.util.Date;


public class ContratPartenariat {
    private int idContrat;
    private Commerce commerce;
    private Date dateDebut;
    private Date dateFin;

    public ContratPartenariat(int idContrat, Commerce commerce, Date dateDebut, Date dateFin) {
        this.idContrat = idContrat;
        this.commerce = commerce;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }


    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }


    public Commerce getCommerce() {
        return commerce;
    }

    public void setCommerce(Commerce commerce) {
        this.commerce = commerce;
    }


    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }


    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}

