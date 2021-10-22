package es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CarPersistence {
    void create(Car car);

    boolean existLicensePlate(String licensePlate);

    void updateOwner(String licensePlate, Owner owner);

    Stream<Car> findByOwnerAndRevision(OwnerEntity owner, Boolean revision);

    Car findByLicensePlate(String licensePlate);
}
