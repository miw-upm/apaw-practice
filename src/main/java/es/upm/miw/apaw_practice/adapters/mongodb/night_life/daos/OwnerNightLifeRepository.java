package es.upm.miw.apaw_practice.adapters.mongodb.night_life.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.night_life.entities.OwnerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface OwnerNightLifeRepository extends MongoRepository<OwnerEntity, String> {

}
