package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CorbeilleTest {
    @Test
    public void testCalculerPoidsTotal() {
        Corbeille corbeille = new Corbeille(1);
        corbeille.listeDechets.add(new Waste(1, "Plastique", 1.5));
        corbeille.listeDechets.add(new Waste(2, "Verre", 2.0));
        assertEquals(3.5, corbeille.calculerPoidsTotal());
    }
}
