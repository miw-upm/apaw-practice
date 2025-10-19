package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.ClassSessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClassSessionRepository extends MongoRepository<ClassSessionEntity, UUID> {
    int deleteByReferenceCode(Integer referenceCode);
    Optional<ClassSessionEntity> findByReferenceCode(Integer referenceCode);


}
