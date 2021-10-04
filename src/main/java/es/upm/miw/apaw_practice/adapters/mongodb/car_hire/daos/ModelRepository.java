package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.ModelEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ModelRepository extends MongoRepository<ModelEntity, String> {
}
