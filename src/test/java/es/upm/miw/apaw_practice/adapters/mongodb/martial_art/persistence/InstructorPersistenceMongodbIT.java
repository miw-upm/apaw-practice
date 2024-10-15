package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.persistence.InstructorPersistenceMongodb;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig

public class InstructorPersistenceMongodbIT {
    @Autowired
    private InstructorPersistenceMongodb instructorPersistenceMongodb;
    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.instructorPersistenceMongodb.read("0"));
    }

    @Test
    void testDniNotExists() {
        assertFalse(this.instructorPersistenceMongodb.existsByDni("0"));
    }

    @Test
    void testDniExists() {
        assertTrue(this.instructorPersistenceMongodb.existsByDni("Z1521143C"));
    }

    @Test
    void testCreateAndRead() {
        Instructor instructor;
        instructor = new Instructor(
                "Z1551645C",
                "Bastian yellow",
                999999,
                LocalDateTime.of(1990, 10, 27, 23, 2, 2));
        this.instructorPersistenceMongodb.create(instructor);
        Instructor instructor1 = this.instructorPersistenceMongodb.read("Z1551645C");
        assertEquals("Z1551645C", instructor1.getDni());;
        assertEquals("Bastian yellow", instructor1.getFullName());
        assertEquals(LocalDateTime.of(1990, 10, 27,  23, 2, 2 ), instructor1.getBirthDate());
    }

    @Test
    void testCreateAndUpdate() {
        Instructor instructor =  new Instructor(
                "Z1441743C",
                "Bastian red",
                999999,
                LocalDateTime.of(1990, 10, 27, 23, 2, 2));
        this.instructorPersistenceMongodb.create(instructor);
        Instructor instructor1 = this.instructorPersistenceMongodb.read("Z1441743C");
        assertEquals("Bastian red", instructor1.getFullName());
        instructor1.setFullName("Bastian sky");
        Instructor instructor2 = this.instructorPersistenceMongodb.update("Z1441743C", instructor1);
        assertEquals("Bastian sky", instructor2.getFullName());
    }
}
