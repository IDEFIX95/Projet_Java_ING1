package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CorbeilleTest {
    @Test
    public void testCalculerPoidsTotal() {
        Corbeille corbeille = new Corbeille(1);
        corbeille.getListeDechets().add(new Dechet(1, "Plastique", 1.5));
        corbeille.getListeDechets().add(new Dechet(2, "Verre", 2.0));
        assertEquals(3.5, corbeille.calculerPoidsTotal());
    }
}
