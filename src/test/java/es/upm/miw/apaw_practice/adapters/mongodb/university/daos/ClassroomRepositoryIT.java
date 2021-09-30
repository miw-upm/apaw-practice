package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class ClassroomRepositoryIT {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.classroomRepository.findAll().stream()
                .anyMatch(classroom ->
                        1302 == classroom.getNumber() &&
                                "ETSISI".equals(classroom.getSchool()) &&
                                20 == classroom.getCapacity()
                ));
    }

}
