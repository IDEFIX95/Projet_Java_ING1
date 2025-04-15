package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommerceTest {
    @Test
    public void testAjoutProduit() {
        Commerce commerce = new Commerce(1, "SuperMarché");
        commerce.listeProduitsDisponibles.add("Sac réutilisable");
        assertEquals(1, commerce.listeProduitsDisponibles.size());
    }
}