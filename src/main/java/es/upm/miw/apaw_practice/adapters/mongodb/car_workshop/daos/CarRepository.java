package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends MongoRepository<CarEntity, String> {
    Optional<CarEntity> findByLicensePlate(String licensePlate);

    List<CarEntity> findByOwner(OwnerEntity owner);
}
