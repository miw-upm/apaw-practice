package es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.IssueReplyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IssueReplyRepository extends MongoRepository<IssueReplyEntity, UUID> {
}
