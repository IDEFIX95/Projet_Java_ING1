package com.projet.projet_java;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
public class ContratPartenariatTest {
    @Test
    public void testCreationContrat() {
        Commerce commerce = new Commerce(1, "ÉcoCommerce");
        ContratPartenariat contrat = new ContratPartenariat(1, commerce, new Date(), new Date());
        assertNotNull(contrat);
    }
}
