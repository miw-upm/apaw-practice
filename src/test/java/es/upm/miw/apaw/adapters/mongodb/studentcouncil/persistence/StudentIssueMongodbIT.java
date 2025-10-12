package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.domain.models.studentcouncil.StudentIssue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class StudentIssuePersistenceMongodbIT {

    @Autowired
    private StudentIssuePersistenceMongodb studentIssuePersistence;

    @Test
    void testCreateStudentIssue() {
        StudentIssue issue = StudentIssue.builder()
                .id(UUID.randomUUID())
                .statement("Test issue")
                .reportDate(LocalDateTime.now())
                .closed(false)
                .urgency(2)
                .build();

        StudentIssue created = studentIssuePersistence.create(issue);

        assertThat(created.getId()).isEqualTo(issue.getId());

        assertThat(created.getReplies()).isEmpty();

        assertThat(created.getStatement()).isEqualTo("Test issue");
        assertThat(created.getClosed()).isFalse();
        assertThat(created.getUrgency()).isEqualTo(2);
    }
}