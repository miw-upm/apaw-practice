package es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.pharmacy.entities.DispensingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DispensingRepository extends MongoRepository<DispensingEntity, String> {

}
