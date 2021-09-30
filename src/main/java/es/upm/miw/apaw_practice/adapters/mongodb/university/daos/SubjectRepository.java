package es.upm.miw.apaw_practice.adapters.mongodb.university.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.university.entities.SubjectEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SubjectRepository extends MongoRepository<SubjectEntity, String> {
    Optional<SubjectEntity> findByReference(Integer reference);
}
