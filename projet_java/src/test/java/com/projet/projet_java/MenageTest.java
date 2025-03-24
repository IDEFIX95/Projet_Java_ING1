package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// Classe de test pour Menage
public class MenageTest {
    @Test
    public void testAjoutDepot() {
        Menage menage = new Menage(1, "Famille Dupont", "10 Rue des Lilas", "email@example.com", "password", "badge123");
        HistoriqueDepot depot = new HistoriqueDepot(1, 1, 1, "12:00", 2.5, 10);
        menage.ajouterDepot(depot);
        assertEquals(10, menage.getPointsFidelity());
    }
}
