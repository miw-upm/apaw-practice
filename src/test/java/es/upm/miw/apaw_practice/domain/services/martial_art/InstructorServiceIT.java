package es.upm.miw.apaw_practice.domain.services.martial_art;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;

import es.upm.miw.apaw_practice.domain.models.martial_art.Instructor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig

public class InstructorServiceIT {

    @Autowired
    private InstructorService instructorService;

    @Test
    void testCreateAndRead() {
        Instructor instructor = new Instructor(
                "187985137",
                "Lee dao lee",
                978949955,
                LocalDateTime.of(1990, 10, 27, 23, 2, 2)
        );
        Instructor instructor1 = this.instructorService.create(instructor);
        assertEquals("187985137", instructor1.getDni());
        assertEquals("Lee dao lee", instructor1.getFullName());
        assertEquals(LocalDateTime.of(1990, 10, 27, 23, 2, 2), instructor1.getBirthDate());
    }

    @Test
    void testRead() {
        Instructor instructor = this.instructorService.read("Z1521143C");
        assertEquals("Z1521143C", instructor.getDni());
    }

    @Test
    void testDelete() {
        Instructor instructor = new Instructor(
                "00914833X",
                "Dao lee",
                978949955,
                LocalDateTime.of(1990, 10, 27, 23, 2, 2)
        );
        this.instructorService.create(instructor);
        this.instructorService.delete("00914833X");
        assertThrows(NotFoundException.class, () -> this.instructorService.read("00914833X"));
    }
}
