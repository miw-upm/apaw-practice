package es.upm.miw.apaw.domain.services.university;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.models.university.Subject;
import es.upm.miw.apaw.domain.persistenceports.university.SubjectPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class SubjectServiceIT {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectPersistence subjectPersistence;

    @Test
    void testCreate() {
        Subject subject = Subject.builder()
                .name("Test Subject Service")
                .description("Test Description Service")
                .credits(6)
                .build();

        Subject createdSubject = this.subjectService.create(subject);

        assertThat(createdSubject).isNotNull();
        assertThat(createdSubject.getName()).isEqualTo("Test Subject Service");
        assertThat(createdSubject.getDescription()).isEqualTo("Test Description Service");
        assertThat(createdSubject.getCredits()).isEqualTo(6);
    }

    @Test
    void testCreateNameConflict() {
        Subject subject = Subject.builder()
                .name("SN001") // This name already exists in the seeder
                .description("Test Description Service")
                .credits(6)
                .build();

        assertThatThrownBy(() -> this.subjectService.create(subject))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining("Name exist: SN001");
    }
}
