package es.upm.miw.apaw.adapters.mongodb.university.daos;

import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SubjectRepositoryIT {

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void testFindByName() {
        String subjectName = "SN001";

        Optional<SubjectEntity> subject = subjectRepository.findByName(subjectName);

        assertThat(subject).isPresent();
        assertThat(subject.get().getName()).isEqualTo(subjectName);
        assertThat(subject.get().getDescription()).isEqualTo("SD001");
        assertThat(subject.get().getCredits()).isEqualTo(6);
    }

    @Test
    void testFindByNameNotFound() {
        String nonExistentName = "NonExistentSubject";

        Optional<SubjectEntity> subject = subjectRepository.findByName(nonExistentName);

        assertThat(subject).isEmpty();
    }
}
