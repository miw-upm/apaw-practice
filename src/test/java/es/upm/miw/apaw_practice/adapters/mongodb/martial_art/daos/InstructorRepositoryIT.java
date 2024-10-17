package es.upm.miw.apaw_practice.adapters.mongodb.martial_art.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.martial_art.entities.InstructorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig

public class InstructorRepositoryIT {
    @Autowired
    private InstructorRepository instructorRepository;

    @Test
    void testFindByDnI() {
        assertTrue(this.instructorRepository.findByDni("Z1521143C").isPresent());
        InstructorEntity instructorEntity = this.instructorRepository.findByDni("Z1521143C").get();
        assertEquals("Bastian red", instructorEntity.getFullName());
        assertEquals(LocalDateTime.of(1990, 10, 27,  23, 2, 2 ), instructorEntity.getBirthDate());
    }
}
