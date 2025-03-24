package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategorieTest {
    @Test
    public void testCategorie() {
        Categorie categorie = new Categorie(1, "Plastique, Métal");
        assertEquals("Plastique, Métal", categorie.getListeCategorie());
    }
}
