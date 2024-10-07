package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;


import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.ManufacturerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ManufacturerRepository extends MongoRepository<ManufacturerEntity, String> {

    Optional<ManufacturerEntity> findByNumberOfEmployees(Integer numberOfEmployees);
}
