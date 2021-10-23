package es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface OwnerPersistence {
    Owner readByDni(String dni);

    OwnerEntity findByName(String name);

    Stream<String> getDniFromCars(Stream<Car> cars);
}
