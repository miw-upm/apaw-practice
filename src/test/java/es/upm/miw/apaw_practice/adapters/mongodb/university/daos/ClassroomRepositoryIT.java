package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ClassroomRepositoryIT {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.classroomRepository.findAll().stream()
                .anyMatch(classroom ->
                        "ETSISI".equals(classroom.getSchool()) &&
                                1302 == classroom.getNumber() &&
                                20 == classroom.getCapacity()
                ));
    }

    @Test
    void testDeleteBySchoolAndNumber() {
        this.classroomRepository.deleteBySchoolAndNumber("ETSISI", 3101);
        assertFalse(this.classroomRepository.findAll().stream()
                .anyMatch(classroom ->
                        "ETSISI".equals(classroom.getSchool()) &&
                                3101 == classroom.getNumber()
                ));
    }

}
