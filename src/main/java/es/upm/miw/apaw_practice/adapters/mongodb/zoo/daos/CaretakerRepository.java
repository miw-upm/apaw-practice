package es.upm.miw.apaw_practice.adapters.mongodb.zoo.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.zoo.entities.CaretakerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CaretakerRepository extends MongoRepository<CaretakerEntity, String> {
}
