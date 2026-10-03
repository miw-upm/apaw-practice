package es.upm.miw.apaw.domain.ports.out.carreservation;

import es.upm.miw.apaw.domain.model.carreservation.Car;

public interface CarGateway {
    Car create(Car car);

    boolean existsByLicensePlate(String licensePlate);
}
