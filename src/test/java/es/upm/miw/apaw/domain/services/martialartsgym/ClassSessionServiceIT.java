package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos.ClassSessionRepository;
import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.ClassSessionEntity;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.ClassSessionPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class ClassSessionServiceIT {

    @Autowired
    private ClassSessionService classSessionService;

    @Autowired
    private ClassSessionPersistence classSessionPersistence;

    @Autowired
    private ClassSessionRepository classSessionRepository;

    @Test
    void testDeleteExistingClassSession() {

        ClassSessionEntity entity = ClassSessionEntity.builder()
                .referenceCode(8888)
                .sessionLength(90)
                .difficultyLevel("Intermediate")
                .dojo(null)
                .attendeeIds(null)
                .build();

        this.classSessionRepository.save(entity);


        this.classSessionService.delete(8888);


        assertThat(this.classSessionRepository.findByReferenceCode(8888)).isEmpty();
    }

    @Test
    void testDeleteNonExistingClassSession() {
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> this.classSessionService.delete(9999));

        assertThat(exception.getMessage()).contains("ClassSession not found");
    }
}
