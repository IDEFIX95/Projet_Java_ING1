package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Bon_AchatTest {
    @Test
    public void testCreationBonAchat() {
        BonAchat bon = new BonAchat(1, 5);
        assertEquals(5, bon.getNombreDeBon());
    }
}

