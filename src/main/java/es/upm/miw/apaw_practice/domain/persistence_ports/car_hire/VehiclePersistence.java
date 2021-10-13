package es.upm.miw.apaw_practice.domain.persistence_ports.car_hire;

import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclePersistence {

    Vehicle readByVinNumber(String vinNumber);

    Vehicle update(Vehicle vehicle);

}
