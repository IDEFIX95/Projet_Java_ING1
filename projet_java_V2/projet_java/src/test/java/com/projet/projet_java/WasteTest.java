package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WasteTest {
    @Test
    public void testPoidsWaste() {
        Waste waste = new Waste(1, "Verre", 2.5);
        assertEquals(2.5, waste.getPoids());
    }
}

