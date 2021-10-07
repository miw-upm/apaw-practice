package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class StudentRepositoryIT {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.studentRepository.findAll().stream()
                .anyMatch(student ->
                         "12345678X".equals(student.getDni()) &&
                                 "Ada Lovelace".equals(student.getFullName()) &&
                                 student.getInternationalStudent()
                ));
    }

}
