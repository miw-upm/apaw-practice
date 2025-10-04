package es.upm.miw.apaw.adapters.mongodb.studentcouncil.daos;

import es.upm.miw.apaw.adapters.mongodb.studentcouncil.entitites.StudentIssueEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface StudentIssueRepository extends MongoRepository<StudentIssueEntity, UUID> {
}
