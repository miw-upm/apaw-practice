package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class StudentPersistanceMongodbIT {

    @Autowired
    private StudentPersistenceMongodb studentPersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> studentPersistence.read("example@example.org"));
    }

    @Test
    void testCodeNotExists() {
        assertFalse(studentPersistence.existEmail("example@example.org"));
    }

    @Test
    void testCodeExists() {
        assertTrue(studentPersistence.existEmail("emily.johnson@example.org"));
    }

    @Test
    void testReadById() {
        Optional<Student> studentOptional = studentPersistence.readAll().filter(degree -> degree.getEmail().equals("sophia.davis@example.org")).findFirst();
        assertTrue(studentOptional.isPresent());
        Student student = studentOptional.get();
        assertEquals("Sophia", student.getFirstName());
        assertEquals("Birmingham", student.getPlaceOfBirth());
        assertEquals(LocalDate.of(2002, 6, 3), student.getEnrollmentDate());
        assertTrue(student.getDegrees().stream().allMatch(degree -> Set.of(2000, 2002, 2004).contains(degree.getCode())));
    }

    @Test
    void testCreateAndUpdate() {
        LocalDate enrollmentDate = LocalDate.of(2002, 6, 3);
        Student student = new Student("rick.sanchez@upm.es", "Rick", "Madrid", enrollmentDate, List.of());
        studentPersistence.create(student);
        Student readedStudent = studentPersistence.read(student.getEmail());
        assertEquals(student, readedStudent);
        assertEquals("rick.sanchez@upm.es", readedStudent.getEmail());
        assertEquals("Rick", readedStudent.getFirstName());
        assertEquals("Madrid", readedStudent.getPlaceOfBirth());
        assertEquals(enrollmentDate, readedStudent.getEnrollmentDate());
        assertTrue(readedStudent.getDegrees().isEmpty());
        LocalDate newEnrollmentDate = LocalDate.of(2010, 1, 1);
        student.setFirstName("Richard");
        student.setPlaceOfBirth("Barcelona");
        student.setEnrollmentDate(newEnrollmentDate);
        studentPersistence.update(student.getEmail(), student);
        readedStudent = studentPersistence.read(student.getEmail());
        assertEquals(student, readedStudent);
        assertEquals("Richard", readedStudent.getFirstName());
        assertEquals("Barcelona", readedStudent.getPlaceOfBirth());
        assertEquals(newEnrollmentDate, readedStudent.getEnrollmentDate());
    }
}
