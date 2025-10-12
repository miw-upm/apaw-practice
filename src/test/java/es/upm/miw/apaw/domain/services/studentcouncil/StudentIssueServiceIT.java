package es.upm.miw.apaw.domain.services.studentcouncil;

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
class StudentIssueServiceIT {

    @Autowired
    private StudentIssueService studentIssueService;

    @Test
    void testCreateStudentIssue() {
        StudentIssue issue = StudentIssue.builder()
                .id(UUID.randomUUID())
                .statement("Integration test issue")
                .reportDate(LocalDateTime.now())
                .closed(false)
                .urgency(3)
                .build();

        StudentIssue created = studentIssueService.createStudentIssue(issue);

        assertThat(created).isNotNull();
        assertThat(created.getId()).isEqualTo(issue.getId());
        assertThat(created.getStatement()).isEqualTo("Integration test issue");
        assertThat(created.getClosed()).isFalse();
        assertThat(created.getUrgency()).isEqualTo(3);
        assertThat(created.getReplies()).isEmpty();
    }

    @Test
    void testUpdateStudentIssueService() {
        UUID id = UUID.fromString("cccccccc-bbbb-cccc-dddd-eeeeffff0001");

        StudentIssue update = StudentIssue.builder()
                .statement("Problem2 updated via service")
                .closed(false)
                .urgency(7)
                .build();

        StudentIssue result = studentIssueService.updateStudentIssue(id, update);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(id);
        assertThat(result.getStatement()).isEqualTo("Problem2 updated via service");
        assertThat(result.getClosed()).isFalse();
        assertThat(result.getUrgency()).isEqualTo(7);
    }
}