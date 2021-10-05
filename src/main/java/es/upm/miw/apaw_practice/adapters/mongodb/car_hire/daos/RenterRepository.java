package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.RenterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RenterRepository extends MongoRepository<RenterEntity, String> {
}
