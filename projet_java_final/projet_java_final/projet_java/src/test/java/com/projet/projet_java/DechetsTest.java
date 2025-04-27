package com.projet.projet_java;

import com.projet.projet_java.dao.DechetDAO;
import com.projet.projet_java.model.Dechet;
import com.projet.projet_java.model.TypeDechet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour l'entité Dechet avec DAO et nouvelle structure.
 */
public class DechetsTest {

    private static DechetDAO dechetDAO;

    @BeforeAll
    public static void setUp() {
        // Prépare l'environnement avant les tests
        dechetDAO = new DechetDAO();
    }

    @Test
    public void testInsertDechetDansBDD() {
        // ⚡ Nouveau constructeur adapté : type, poids, idPoubelle, idMenage
        Dechet dechet = new Dechet(TypeDechet.VERRE, 2.5, 1, 1); // Exemple : poubelleId=1, menageId=1

        int idGenere = dechetDAO.insert(dechet);

        assertTrue(idGenere > 0, "❌ Insertion échouée, id non généré");
        System.out.println("✅ Déchet inséré avec succès dans la base avec l'id " + idGenere);
    }

    @Test
    public void testPoidsDeDechet() {
        Dechet dechet = new Dechet(TypeDechet.VERRE, 2.5, 1, 1);
        assertEquals(2.5, dechet.getPoids(), 0.01, "❌ Mauvais poids du déchet");
    }
}
