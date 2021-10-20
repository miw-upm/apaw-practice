package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.CupsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CupsRepository extends MongoRepository<CupsEntity, String> {
    Optional<CupsEntity> findByCups(String cups);
}
