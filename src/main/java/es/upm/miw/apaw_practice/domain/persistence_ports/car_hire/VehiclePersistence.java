package es.upm.miw.apaw_practice.domain.persistence_ports.car_hire;

import es.upm.miw.apaw_practice.adapters.mongodb.car_hire.entities.VehicleEntity;
import es.upm.miw.apaw_practice.domain.models.car_hire.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclePersistence {

    VehicleEntity readByVinNumber(String vinNumber);

    Vehicle update(Vehicle vehicle);

}
