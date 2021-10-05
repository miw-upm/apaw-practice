package es.upm.miw.apaw_practice.domain.services.car_workshop;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.CarPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarPersistence carPersistence;

    @Autowired
    public CarService(CarPersistence carPersistence){
        this.carPersistence = carPersistence;
    }

    public void create(Car car) {
        this.assertLicensePlateNotExist(car.getLicensePlate());
        this.carPersistence.create(car);
    }

    public void assertLicensePlateNotExist(String licensePlate){
        if (this.carPersistence.existLicensePlate(licensePlate)){
            throw new ConflictException("LicensePlate exists: " + licensePlate);
        }
    }
}
