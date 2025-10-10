package es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.IssueReplyEntity;
import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentIssueEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class StudentIssueRepositoryIT {

    @Autowired
    private StudentIssueRepository studentIssueRepository;

    @Test
    void testCreateAndRead() {

        IssueReplyEntity reply1 = IssueReplyEntity.builder()
                .id(UUID.randomUUID())
                .reason("Reply1")
                .createDate(LocalDateTime.now().minusDays(1))
                .compensation(null)
                .build();

        IssueReplyEntity reply2 = IssueReplyEntity.builder()
                .id(UUID.randomUUID())
                .reason("Reply2")
                .createDate(LocalDateTime.now())
                .compensation(null)
                .build();


        StudentIssueEntity issue = StudentIssueEntity.builder()
                .id(UUID.randomUUID())
                .statement("Test issue")
                .reportDate(LocalDateTime.now())
                .closed(false)
                .urgency(2)
                .replies(Arrays.asList(reply1, reply2))
                .build();

        this.studentIssueRepository.save(issue);

        StudentIssueEntity found = this.studentIssueRepository.findById(issue.getId()).orElseThrow();
        assertThat(found.getStatement()).isEqualTo("Test issue");
        assertThat(found.getClosed()).isFalse();
        assertThat(found.getUrgency()).isEqualTo(2);
        assertThat(found.getReplies()).extracting(IssueReplyEntity::getReason)
                .containsExactlyInAnyOrder("Reply1", "Reply2");
    }
}