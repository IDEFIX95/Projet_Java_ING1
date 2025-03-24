package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PoubelleIntelligenteTest {
    @Test
    public void testEstPlein() {
        PoubelleIntelligente poubelle = new PoubelleIntelligente(1, "Plastique", 5.0);
        assertFalse(poubelle.estPlein());
    }
}
