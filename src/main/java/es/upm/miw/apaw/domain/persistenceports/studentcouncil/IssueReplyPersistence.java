package es.upm.miw.apaw.domain.persistenceports.studentcouncil;

import java.util.UUID;

public interface IssueReplyPersistence {
    void deleteById(UUID replyId);
}
