package es.upm.miw.apaw.adapters.mongodb.university.daos;

import es.upm.miw.apaw.adapters.mongodb.university.entities.EnrollmentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EnrollmentRepositoryIT {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Test
    void testFindByCode() {
        String enrollmentCode = "ENR001";

        Optional<EnrollmentEntity> enrollment = enrollmentRepository.findByCode(enrollmentCode);

        assertThat(enrollment).isPresent();
        assertThat(enrollment.get().getCode()).isEqualTo(enrollmentCode);
        assertThat(enrollment.get().getStudentId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"));
    }

    @Test
    void testFindByCodeNotFound() {
        String nonExistentCode = "NONEXISTENT";

        Optional<EnrollmentEntity> enrollment = enrollmentRepository.findByCode(nonExistentCode);

        assertThat(enrollment).isEmpty();
    }

    @Test
    void testDeleteByCode() {
        String enrollmentCode = "ENR002";

        Optional<EnrollmentEntity> enrollmentBefore = enrollmentRepository.findByCode(enrollmentCode);
        assertThat(enrollmentBefore).isPresent();

        int deletedCount = enrollmentRepository.deleteByCode(enrollmentCode);

        assertThat(deletedCount).isGreaterThan(0);

        Optional<EnrollmentEntity> enrollmentAfter = enrollmentRepository.findByCode(enrollmentCode);
        assertThat(enrollmentAfter).isEmpty();
    }

    @Test
    void testDeleteByCodeNotFound() {
        String nonExistentCode = "NONEXISTENT";

        int deletedCount = enrollmentRepository.deleteByCode(nonExistentCode);

        assertThat(deletedCount).isZero();
    }
}
