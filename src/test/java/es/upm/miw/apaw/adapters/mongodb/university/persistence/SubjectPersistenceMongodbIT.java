package es.upm.miw.apaw.adapters.mongodb.university.persistence;

import es.upm.miw.apaw.adapters.mongodb.university.daos.SubjectRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectEntity;
import es.upm.miw.apaw.domain.models.university.Subject;
import es.upm.miw.apaw.domain.persistenceports.university.SubjectPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SubjectPersistenceMongodbIT {

    @Autowired
    private SubjectPersistence subjectPersistence;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void testCreate() {
        Subject subject = Subject.builder()
                .name("Test Subject")
                .description("Test Description")
                .credits(6)
                .build();

        Subject createdSubject = subjectPersistence.create(subject);

        assertThat(createdSubject).isNotNull();
        assertThat(createdSubject.getName()).isEqualTo("Test Subject");
        assertThat(createdSubject.getDescription()).isEqualTo("Test Description");
        assertThat(createdSubject.getCredits()).isEqualTo(6);

        Optional<SubjectEntity> subjectEntity = subjectRepository.findByName("Test Subject");
        assertThat(subjectEntity).isPresent();
        assertThat(subjectEntity.get().getName()).isEqualTo("Test Subject");
    }

    @Test
    void testExistNameTrue() {
        boolean exists = subjectPersistence.existName("SN002");
        assertThat(exists).isTrue();
    }

    @Test
    void testExistNameFalse() {
        boolean exists = subjectPersistence.existName("NonExistentSubject");
        assertThat(exists).isFalse();
    }

    @Test
    void testCreateWithExistingName() {
        Subject subject = Subject.builder()
                .name("Mathematics")
                .description("Another Math Subject")
                .credits(4)
                .build();

        Subject createdSubject = subjectPersistence.create(subject);

        assertThat(createdSubject).isNotNull();
        assertThat(createdSubject.getName()).isEqualTo("Mathematics");
        assertThat(createdSubject.getDescription()).isEqualTo("Another Math Subject");
        assertThat(createdSubject.getCredits()).isEqualTo(4);
    }
}
