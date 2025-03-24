package com.projet.projet_java;

import java.util.ArrayList;
import java.util.List;
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
}
