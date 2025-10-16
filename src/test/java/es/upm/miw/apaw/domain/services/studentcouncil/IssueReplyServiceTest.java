package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.domain.persistenceports.studentcouncil.IssueReplyPersistence;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class IssueReplyServiceTest {

    @MockitoBean
    private IssueReplyPersistence issueReplyPersistence;

    @Autowired
    private IssueReplyService issueReplyService;

    @Test
    void testDeleteIssueReplyOk() {
        UUID replyId = UUID.randomUUID();

        Mockito.doNothing().when(issueReplyPersistence).deleteById(replyId);

        issueReplyService.deleteIssueReply(replyId);

        Mockito.verify(issueReplyPersistence, Mockito.times(1)).deleteById(replyId);
    }
}