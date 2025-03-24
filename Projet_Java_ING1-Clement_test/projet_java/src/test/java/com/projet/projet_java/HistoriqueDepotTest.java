package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HistoriqueDepotTest {
    @Test
    public void testHistoriqueDepotCreation() {
        HistoriqueDepot depot = new HistoriqueDepot(1, 1, 1, "14:30", 3.0, 15);
        assertEquals(15, depot.getPointsGagnes());
    }
}
