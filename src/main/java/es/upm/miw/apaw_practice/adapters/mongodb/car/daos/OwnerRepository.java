package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;


import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.OwnerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OwnerRepository extends MongoRepository<OwnerEntity, String> {

    Optional<OwnerEntity> findByDriverLicense(String driverLicense);
}
