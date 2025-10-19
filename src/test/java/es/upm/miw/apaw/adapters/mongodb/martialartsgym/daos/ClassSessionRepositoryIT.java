package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.ClassSessionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ClassSessionRepositoryIT {

    @Autowired
    private ClassSessionRepository classSessionRepository;

    @Test
    void testDeleteByReferenceCode() {
        ClassSessionEntity session = ClassSessionEntity.builder()
                .referenceCode(9999)
                .sessionLength(90)
                .difficultyLevel("Intermediate")
                .dojo(null)
                .attendeeIds(null)
                .build();

        this.classSessionRepository.save(session);

        this.classSessionRepository.delete(session);

        assertThat(this.classSessionRepository.findByReferenceCode(9999)).isEmpty();
    }
}
