package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.TeacherEntity;
import es.upm.miw.apaw_practice.domain.models.university.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class TeacherRepositoryIT {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    void testFindByNationalIdNonExisting() {
        assertTrue(teacherRepository.findByNationalId("UK007").isEmpty());
    }

    @Test
    void testFindByNationalId() {
        Optional<TeacherEntity> teacherEntity = teacherRepository.findByNationalId("AB987654C");
        assertFalse(teacherEntity.isEmpty());
        assertNotNull(teacherEntity.get());
        Teacher teacher = teacherEntity.get().toTeacher();
        assertEquals("Anderson", teacher.getLastName());
        assertEquals(LocalDate.of(1976, 1, 2), teacher.getBirthDate());
        assertEquals("ox.ac.uk", teacher.getUniversity().getTopDomain());
    }
}