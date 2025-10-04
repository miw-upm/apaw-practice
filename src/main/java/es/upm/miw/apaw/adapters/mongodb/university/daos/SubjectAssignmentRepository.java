package es.upm.miw.apaw.adapters.mongodb.university.daos;

import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectAssignmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SubjectAssignmentRepository extends MongoRepository<SubjectAssignmentEntity, UUID> {
}

