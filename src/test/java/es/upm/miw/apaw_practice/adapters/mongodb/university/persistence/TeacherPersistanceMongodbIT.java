package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.university.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class TeacherPersistanceMongodbIT {

    @Autowired
    private TeacherPersistenceMongodb teacherPersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> teacherPersistence.read("UK007"));
    }

    @Test
    void testCodeNotExists() {
        assertFalse(teacherPersistence.existNationalId("UK007"));
    }

    @Test
    void testCodeExists() {
        assertTrue(teacherPersistence.existNationalId("EF654321D"));
    }

    @Test
    void testReadById() {
        Optional<Teacher> studentOptional = teacherPersistence.readAll().filter(degree -> degree.getNationalId().equals("EF654321D")).findFirst();
        assertTrue(studentOptional.isPresent());
        Teacher teacher = studentOptional.get();
        assertEquals("Thompson", teacher.getLastName());
        assertEquals(LocalDate.of(1991, 3, 23), teacher.getBirthDate());
        assertEquals("manchester.ac.uk", teacher.getUniversity().getTopDomain());
    }

    @Test
    void testCreateAndUpdate() {
        LocalDate birthDate = LocalDate.of(1970, 1, 1);
        Teacher teacher = new Teacher("UK1234", birthDate, "Sanchez", null);
        teacherPersistence.create(teacher);
        Teacher readedTeacher = teacherPersistence.read(teacher.getNationalId());
        assertEquals(teacher, readedTeacher);
        assertEquals("UK1234", readedTeacher.getNationalId());
        assertEquals(birthDate, readedTeacher.getBirthDate());
        assertEquals("Sanchez", readedTeacher.getLastName());
        assertNull(readedTeacher.getUniversity());
        LocalDate newBirthDate = LocalDate.of(1980, 2, 2);
        teacher.setLastName("Smith");
        teacher.setBirthDate(newBirthDate);
        teacherPersistence.update(teacher.getNationalId(), teacher);
        readedTeacher = teacherPersistence.read(teacher.getNationalId());
        assertEquals(teacher, readedTeacher);
        assertEquals("Smith", readedTeacher.getLastName());
        assertEquals(newBirthDate, readedTeacher.getBirthDate());
    }
}
