package es.upm.miw.apaw_practice.adapters.mongodb.university.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.university.daos.ClassroomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
public class ClassroomPersistenceMongodbIT {

    @Autowired
    private ClassroomPersistenceMongodb classroomPersistenceMongodb;

    @Autowired
    private ClassroomRepository classroomRepository;

    @Test
    void testDelete() {
        this.classroomPersistenceMongodb.delete("ETSISI", 3101);
        assertFalse(this.classroomRepository.findAll().stream()
                .anyMatch(classroom ->
                        "ETSISI".equals(classroom.getSchool()) &&
                                3101 == classroom.getNumber()
                ));
    }
}
