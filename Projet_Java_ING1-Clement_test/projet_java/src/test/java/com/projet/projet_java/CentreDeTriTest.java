package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CentreDeTriTest {
    @Test
    public void testAjoutPoubelle() {
        CentreDeTri centre = new CentreDeTri(1, "Centre Ville", "10 Rue Centrale");
        PoubelleIntelligente poubelle = new PoubelleIntelligente(1, "Plastique", 10.0);
        centre.getListePoubelles().add(poubelle);
        assertEquals(1, centre.getListePoubelles().size());
    }
}
