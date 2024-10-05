package es.upm.miw.apaw_practice.adapters.mongodb.military.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.military.entities.UnitEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UnitRepository extends MongoRepository<UnitEntity, String> {
}
