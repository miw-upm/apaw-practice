package es.upm.miw.apaw_practice.domain.models.cinema;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectorTest {

    @Test
    void testDirectorConstructorAndGetters() {
        Director director = new Director("1", "Pedro Almodóvar", "98765432Z", "1960-05-20");

        assertEquals("1", director.getId());
        assertEquals("Pedro Almodóvar", director.getName());
        assertEquals("98765432Z", director.getDni());
        assertEquals("1960-05-20", director.getBirthdate());
    }

    @Test
    void testDirectorSetters() {
        Director director = new Director();
        director.setId("2");
        director.setName("Alejandro Amenábar");
        director.setDni("12345678A");
        director.setBirthdate("1972-03-31");

        assertEquals("2", director.getId());
        assertEquals("Alejandro Amenábar", director.getName());
        assertEquals("12345678A", director.getDni());
        assertEquals("1972-03-31", director.getBirthdate());
    }
}