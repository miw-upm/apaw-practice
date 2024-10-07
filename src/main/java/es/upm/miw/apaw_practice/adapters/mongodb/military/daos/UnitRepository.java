package es.upm.miw.apaw_practice.adapters.mongodb.military.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.UnitEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UnitRepository extends MongoRepository<UnitEntity, String> {
    Optional<UnitEntity> findByName(String name);
}
