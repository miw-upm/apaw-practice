package es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Tyre;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TyrePersistence {
    void deleteManufacturer(String manufacturer);

    void create(Tyre tyre);

    Tyre read(String model);

    Stream<String> findDistinctModelByCar(Stream<Car> cars);
}
