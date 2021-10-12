package es.upm.miw.apaw_practice.adapters.mongodb.emarketer.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.emarketer.entities.EmarketerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmarketerRepository extends MongoRepository<EmarketerEntity, String> {
}
