package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DechetsTest {
    @Test
    public void testPoidsDechet() {
        Dechet dechet = new Dechet(1, "Verre", 2.5);
        assertEquals(2.5, dechet.getPoids());
    }
}

