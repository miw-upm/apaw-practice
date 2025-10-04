package es.upm.miw.apaw.adapters.mongodb.student_council.daos;

import es.upm.miw.apaw.adapters.mongodb.student_council.entitites.StudentCouncilEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface StudentCouncilRepository extends MongoRepository<StudentCouncilEntity, UUID> {
}
