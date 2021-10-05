package es.upm.miw.apaw_practice.domain.services.car_workshop;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.CarPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.OwnerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    private final CarPersistence carPersistence;
    private final OwnerPersistence ownerPersistence;

    @Autowired
    public CarService(CarPersistence carPersistence, OwnerPersistence ownerPersistence){
        this.carPersistence = carPersistence;
        this.ownerPersistence = ownerPersistence;
    }

    public void createWithOwnerDni(String dni, Car car) {
        this.assertLicensePlateNotExist(car.getLicensePlate());
        car.setOwner(this.ownerPersistence.getFromDni(dni));
        this.carPersistence.create(car);
    }

    public void assertLicensePlateNotExist(String licensePlate){
        if (this.carPersistence.existLicensePlate(licensePlate)){
            throw new ConflictException("LicensePlate exists: " + licensePlate);
        }
    }

}
