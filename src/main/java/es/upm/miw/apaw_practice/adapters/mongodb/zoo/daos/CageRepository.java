package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CageRepository extends MongoRepository<CageEntity, String> {

}
