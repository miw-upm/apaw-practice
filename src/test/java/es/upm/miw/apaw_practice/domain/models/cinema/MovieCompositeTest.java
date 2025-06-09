package es.upm.miw.apaw_practice.domain.models.cinema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieCompositeTest {

    @Test
    void testComposite() {
        MovieComponent movie1 = new MovieLeaf("Matrix", true);
        MovieComponent movie2 = new MovieLeaf("Inception", false);
        MovieComponent saga = new MovieComposite();

        saga.add(movie1);
        saga.add(movie2);

        // Si isThreeDFormat devuelve true si al menos uno es 3D:
        assertTrue(saga.isThreeDFormat());

        // Si getTitle concatena títulos con " & "
        assertEquals("Matrix & Inception", saga.getTitle());

        // Primer hijo es movie1
        assertEquals(movie1, saga.getChild(0));

        // Segundo hijo es movie2
        assertEquals(movie2, saga.getChild(1));

        // El tamaño debería ser 2
        assertEquals(2, ((MovieComposite) saga).numberOfChildren());
    }
}