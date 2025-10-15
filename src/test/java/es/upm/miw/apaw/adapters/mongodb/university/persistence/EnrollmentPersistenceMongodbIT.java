package es.upm.miw.apaw.adapters.mongodb.university.persistence;

import es.upm.miw.apaw.adapters.mongodb.university.daos.EnrollmentRepository;
import es.upm.miw.apaw.adapters.mongodb.university.entities.EnrollmentEntity;
import es.upm.miw.apaw.domain.persistenceports.university.EnrollmentPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EnrollmentPersistenceMongodbIT {

    @Autowired
    private EnrollmentPersistence enrollmentPersistence;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    void testDelete() {
        String enrollmentCode = "ENR003";

        Optional<EnrollmentEntity> enrollmentBefore = enrollmentRepository.findByCode(enrollmentCode);
        assertThat(enrollmentBefore).isPresent();

        enrollmentPersistence.delete(enrollmentCode);

        Optional<EnrollmentEntity> enrollmentAfter = enrollmentRepository.findByCode(enrollmentCode);
        assertThat(enrollmentAfter).isEmpty();
    }

    @Test
    void testDeleteNonExistent() {
        String nonExistentCode = "NONEXISTENT";

        Optional<EnrollmentEntity> enrollmentBefore = enrollmentRepository.findByCode(nonExistentCode);
        assertThat(enrollmentBefore).isEmpty();

        enrollmentPersistence.delete(nonExistentCode);

        Optional<EnrollmentEntity> enrollmentAfter = enrollmentRepository.findByCode(nonExistentCode);
        assertThat(enrollmentAfter).isEmpty();
    }
}
