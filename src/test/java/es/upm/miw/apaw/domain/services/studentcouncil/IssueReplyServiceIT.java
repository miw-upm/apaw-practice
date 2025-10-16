package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.IssueReplyRepository;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.IssueReplyPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class IssueReplyServiceIT {

    @Autowired
    private IssueReplyPersistence issueReplyPersistence;

    @Autowired
    private IssueReplyRepository issueReplyRepository;

    @Test
    void testDeleteIssueReply_success() {
        UUID id = UUID.fromString("bbbbbbbb-bbbb-cccc-dddd-eeeeffff0000");

        assertThat(this.issueReplyRepository.existsById(id)).isTrue();

        this.issueReplyPersistence.deleteById(id);

        assertThat(this.issueReplyRepository.existsById(id)).isFalse();
    }
}
