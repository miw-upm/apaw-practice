package es.upm.miw.apaw.domain.services.studentcouncil;

import es.upm.miw.apaw.domain.persistenceports.studentcouncil.IssueReplyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IssueReplyService {

    private final IssueReplyPersistence issueReplyPersistence;

    @Autowired
    public IssueReplyService(IssueReplyPersistence issueReplyPersistence) {
        this.issueReplyPersistence = issueReplyPersistence;
    }

    public void deleteIssueReply(UUID replyId) {
        this.issueReplyPersistence.deleteById(replyId);
    }
}