package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.WaiterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WaiterRepository extends MongoRepository<WaiterEntity,String> {
}
