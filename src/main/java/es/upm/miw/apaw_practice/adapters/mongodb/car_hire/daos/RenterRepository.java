package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.RenterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RenterRepository extends MongoRepository<RenterEntity, String> {
    Optional<RenterEntity>findByDni(String dni);
}
