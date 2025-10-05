package es.upm.miw.apaw.adapters.mongodb.recruiting.daos;

import es.upm.miw.apaw.adapters.mongodb.recruiting.entities.PositionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface PositionRepository extends MongoRepository<PositionEntity, UUID> {

    Optional<PositionEntity> findTopByOrderByReferenceDesc();
}
