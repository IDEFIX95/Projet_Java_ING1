package com.projet.projet_java;

import com.projet.projet_java.model.BonAchat;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class Bon_AchatTest {

    @Test
    void testCreationBon() {
        // Création d'un bon avec données fictives
        BonAchat bon = new BonAchat(
                1,     // idBonAchat
                2,     // idMenage
                3,     // idCommerce
                50,    // points utilisés
                LocalDate.now(),
                LocalDate.now().plusDays(30),
                "Alimentation"
        );

        assertEquals(1, bon.getIdBonAchat());
        assertEquals(2, bon.getIdMenage());
        assertEquals(3, bon.getIdCommerce());
        assertEquals(50, bon.getPointsUtilises());
        assertEquals("Alimentation", bon.getCategorie());
        assertTrue(bon.estValide());
    }

    @Test
    void testExpirationBon() {
        // Bon déjà expiré
        BonAchat bonExpire = new BonAchat(
                1,
                2,
                3,
                50,
                LocalDate.now().minusDays(40),
                LocalDate.now().minusDays(10),
                "Alimentation"
        );

        assertFalse(bonExpire.estValide());
    }
}
