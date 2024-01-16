package es.upm.miw.apaw_practice.domain.services.padel_academy;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.padel_academy.PadelAcademySeederService;
import es.upm.miw.apaw_practice.domain.models.padel_academy.Instructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class InstructorServiceIT {
    @Autowired
    private InstructorService instructorService;
    @Autowired
    private PadelAcademySeederService padelAcademySeederService;

    @AfterEach
    void resetDatabase() {
        this.padelAcademySeederService.deleteAll();
        this.padelAcademySeederService.seedDatabase();
    }

    @Test
    void testUpdate(){
        String dni =  "13579086B";
        Integer phoneNumber = 123456789;
        Instructor instructor = instructorService.updatePhoneNumber(dni, phoneNumber);
        assertNotNull(instructor);
        assertEquals(phoneNumber, instructor.getPhoneNumber());
    }

    @Test
    void testFindInstructorsNamesByCourtSurface() {
        List<String> names = this.instructorService.findInstructorsNamesByCourtSurface("clay");
        assertTrue(names.contains("Ana"));
    }

    @Test
    void testReadByName() {
        Instructor instructor = instructorService.read("Ana");
        assertNotNull(instructor);
        assertEquals("12345678A", instructor.getDni());
        assertEquals("Ana", instructor.getName());
        assertEquals(654456545, instructor.getPhoneNumber());
    }
}
