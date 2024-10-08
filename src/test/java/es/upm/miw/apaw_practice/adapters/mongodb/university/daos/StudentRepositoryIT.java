package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.StudentEntity;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class StudentRepositoryIT {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testFindByEmailNotExists() {
        assertTrue(studentRepository.findByEmail("i.dont.exist@example.org").isEmpty());
    }

    @Test
    void testFindByEmail() {
        Optional<StudentEntity> studentEntity = studentRepository.findByEmail("sophia.davis@example.org");
        assertFalse(studentEntity.isEmpty());
        assertNotNull(studentEntity.get());
        Student student = studentEntity.get().toStudent();
        assertEquals("Sophia", student.getFirstName());
        assertEquals("Birmingham", student.getPlaceOfBirth());
        assertEquals(LocalDate.of(2002, 6, 3), student.getEnrollmentDate());
        assertEquals(3, student.getEnrolledDegrees().size());
    }
}