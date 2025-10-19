package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.ClassSessionRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.ClassSessionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class ClassSessionPersistenceMongodbIT {

    @Autowired
    private ClassSessionPersistenceMongodb classSessionPersistenceMongodb;

    @Autowired
    private ClassSessionRepository classSessionRepository;

    @Test
    void testDeleteExistingSession() {
        ClassSessionEntity entity = ClassSessionEntity.builder()
                .referenceCode(7777)
                .sessionLength(60)
                .difficultyLevel("Beginner")
                .dojo(null)
                .attendeeIds(null)
                .build();
        classSessionRepository.save(entity);

        classSessionPersistenceMongodb.delete(7777);

        assertThat(classSessionRepository.findByReferenceCode(7777)).isEmpty();
    }

    @Test
    void testDeleteNonExistingSession() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> classSessionPersistenceMongodb.delete(1234));
        assertThat(exception.getMessage()).contains("ClassSession not found");
    }
}
