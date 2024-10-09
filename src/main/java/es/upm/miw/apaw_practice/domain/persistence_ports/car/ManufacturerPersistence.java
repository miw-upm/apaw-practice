package es.upm.miw.apaw_practice.domain.persistence_ports.car;

import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;

import java.util.stream.Stream;

@Repository
public interface ManufacturerPersistence {

    Stream<Manufacturer> readAll();

    Manufacturer readByName(String name);

    Manufacturer update(String name, Manufacturer manufacturer);

    boolean existName(String name);
}
