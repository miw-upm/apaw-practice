package es.upm.miw.apaw_practice.domain.persistence_ports.car;


import es.upm.miw.apaw_practice.domain.models.car.Car;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


@Repository
public interface CarPersistence {


    Car readByModel(String model);

    Car create(Car car);

    void delete(String model);

    BigDecimal getTotalCostByDriverLicense(String driverLicense);
}
