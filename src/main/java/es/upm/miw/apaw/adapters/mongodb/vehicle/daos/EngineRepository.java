package es.upm.miw.apaw.adapters.mongodb.vehicle.daos;

import es.upm.miw.apaw.adapters.mongodb.vehicle.entities.EngineEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface EngineRepository extends MongoRepository<EngineEntity, UUID> {
    Optional<EngineEntity> findByCodeEngine(String codeEngine);
}
