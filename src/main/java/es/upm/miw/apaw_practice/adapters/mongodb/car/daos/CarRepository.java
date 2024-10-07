package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;


import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.CarEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CarRepository extends MongoRepository<CarEntity, String> {

    Optional<CarEntity> findByModel(String model);
}
