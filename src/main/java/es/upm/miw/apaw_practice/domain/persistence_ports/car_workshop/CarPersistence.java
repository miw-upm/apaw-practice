package es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;

public interface CarPersistence {
    void create(Car car);

    boolean existLicensePlate(String licensePlate);
}
