package es.upm.miw.apaw_practice.domain.persistence_ports.car;


import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;

import java.math.BigDecimal;

@Repository
public interface OwnerCarPersistence {

    OwnerCar readByDriverLicense(String driverLicense);

    OwnerCar updateName(String driverLicense, String name);

    BigDecimal getTotalCostByDriverLicense(String driverLicense);
}
