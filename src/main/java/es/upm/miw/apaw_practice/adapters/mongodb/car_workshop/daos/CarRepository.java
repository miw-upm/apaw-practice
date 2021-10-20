package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.CarEntity;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public interface CarRepository extends MongoRepository<CarEntity, String> {
    Optional<CarEntity> findByLicensePlate(String licensePlate);

    Stream<CarEntity> findByOwner(Owner owner);
}
