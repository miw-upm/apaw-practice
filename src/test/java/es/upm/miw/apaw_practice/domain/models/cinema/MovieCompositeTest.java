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

        assertTrue(saga.isThreeDFormat());
        assertEquals("Matrix & Inception", saga.getTitle());
        assertEquals(movie1, saga.getChild(0));
    }
}