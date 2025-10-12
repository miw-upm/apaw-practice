package es.upm.miw.apaw.adapters.mongodb.studentcouncil.persistence;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos.IssueReplyRepository;
import es.upm.miw.apaw.domain.persistenceports.studentcouncil.IssueReplyPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("issueReplyPersistence")
public class IssueReplyPersistenceMongodb implements IssueReplyPersistence {

    private final IssueReplyRepository issueReplyRepository;

    @Autowired
    public IssueReplyPersistenceMongodb(IssueReplyRepository issueReplyRepository) {
        this.issueReplyRepository = issueReplyRepository;
    }

    @Override
    public void deleteById(UUID id) {
        this.issueReplyRepository.findById(id)
                .ifPresentOrElse(
                        this.issueReplyRepository::delete,
                        () -> { throw new RuntimeException("IssueReply not found with id: " + id); }
                );
    }
}