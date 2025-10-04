package es.upm.miw.apaw.adapters.mongodb.university.daos;

import es.upm.miw.apaw.adapters.mongodb.university.entities.SubjectEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SubjectRepository extends MongoRepository<SubjectEntity, UUID> {
}

