package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.OwnerCarEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OwnerCarRepository extends MongoRepository<OwnerCarEntity, String> {

    Optional<OwnerCarEntity> findByDriverLicense(String driverLicense);
}
