package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.CupsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CupsRepository extends MongoRepository<CupsEntity, String> {
}
