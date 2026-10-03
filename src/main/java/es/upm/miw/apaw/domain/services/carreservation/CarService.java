package es.upm.miw.apaw.domain.services.carreservation;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.carreservation.Car;
import es.upm.miw.apaw.domain.ports.out.carreservation.CarGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarGateway carGateway;

    public Car create(Car car) {

        if (this.carGateway.existsByLicensePlate(car.getLicensePlate())) {
            throw new ConflictException(
                    "License plate already exists: " + car.getLicensePlate()
            );
        }

        return this.carGateway.create(car);
    }
}