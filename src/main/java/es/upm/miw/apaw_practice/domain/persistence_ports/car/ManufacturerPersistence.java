package es.upm.miw.apaw_practice.domain.persistence_ports.car;

import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.models.car.Manufacturer;

@Repository
public interface ManufacturerPersistence {

    Manufacturer readByName(String name);
}
