package es.upm.miw.apaw.domain.ports.out.carreservation;

import es.upm.miw.apaw.domain.model.carreservation.Car;

import java.util.Optional;
import java.util.UUID;

public interface CarGateway {
    Car create(Car car);

    boolean existsByLicensePlate(String licensePlate);

    Optional<Car> read(UUID id);

    Car update(UUID id, Car car);
}
