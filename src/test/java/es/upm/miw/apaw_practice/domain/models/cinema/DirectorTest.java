package es.upm.miw.apaw_practice.domain.models.cinema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectorTest {

    @Test
    void testDirectorConstructorAndGetters() {
        // Constructor con 5 argumentos
        Director director = new Director("1", "Pedro Almodóvar", "98765432Z", "1960-05-20", "Drama");

        assertEquals("1", director.getId());
        assertEquals("Pedro Almodóvar", director.getName());
        assertEquals("98765432Z", director.getDni());
        assertEquals("1960-05-20", director.getBirthdate());
        assertEquals("Drama", director.getStyle());
    }

    @Test
    void testDirectorSetters() {
        Director director = new Director();
        director.setId("2");
        director.setName("Alejandro Amenábar");
        director.setDni("12345678A");
        director.setBirthdate("1972-03-31");
        director.setStyle("Thriller");

        assertEquals("2", director.getId());
        assertEquals("Alejandro Amenábar", director.getName());
        assertEquals("12345678A", director.getDni());
        assertEquals("1972-03-31", director.getBirthdate());
        assertEquals("Thriller", director.getStyle());
    }

    @Test
    void testDirectorBuilder() {
        Director director = new Director.Builder()
                .id("3")
                .name("Guillermo del Toro")
                .dni("87654321B")
                .birthdate("1964-10-09")
                .style("Fantasy")
                .build();

        assertEquals("3", director.getId());
        assertEquals("Guillermo del Toro", director.getName());
        assertEquals("87654321B", director.getDni());
        assertEquals("1964-10-09", director.getBirthdate());
        assertEquals("Fantasy", director.getStyle());
    }
}