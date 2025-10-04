package es.upm.miw.apaw.adapters.mongodb.fighters.daos;

import es.upm.miw.apaw.adapters.mongodb.fighters.entities.CoachEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CoachRepository extends MongoRepository<CoachEntity, UUID> {
    Optional<CoachEntity> findByFullName(String fullName);
}
