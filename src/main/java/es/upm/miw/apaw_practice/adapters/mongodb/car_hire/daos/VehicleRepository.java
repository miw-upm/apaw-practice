package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.VehicleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VehicleRepository extends MongoRepository<VehicleEntity, String> {
    Optional<VehicleEntity>findByVinNumber(String vinNumber);
}
