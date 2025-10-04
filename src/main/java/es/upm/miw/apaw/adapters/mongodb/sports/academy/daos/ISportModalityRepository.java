package es.upm.miw.apaw.adapters.mongodb.sports.academy.daos;

import es.upm.miw.apaw.adapters.mongodb.sports.academy.entities.SportModalityEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ISportModalityRepository extends MongoRepository<SportModalityEntity, UUID> {
    Optional<SportModalityEntity> findBySportId(UUID sportId);
}
