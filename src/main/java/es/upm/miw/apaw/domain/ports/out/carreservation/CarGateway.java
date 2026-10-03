package es.upm.miw.apaw.domain.ports.out.carreservation;

import es.upm.miw.apaw.domain.model.carreservation.Car;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarGateway {
    Car create(Car car);

    boolean existsByLicensePlate(String licensePlate);

    Optional<Car> read(UUID id);

    Car update(Car car);

    void delete(UUID id);

    boolean isUsedByReservation(UUID id);

    List<Car> findAll();
}
