package es.upm.miw.apaw_practice.domain.services.car;


import es.upm.miw.apaw_practice.domain.models.car.OwnerCar;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.OwnerCarPersistence;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OwnerCarService {

    private final OwnerCarPersistence ownerPersistence;

    public OwnerCarService(OwnerCarPersistence ownerPersistence) {
        this.ownerPersistence = ownerPersistence;
    }

    public OwnerCar read(String driverLicense){
        return ownerPersistence.readByDriverLicense(driverLicense);
    }

    public OwnerCar updateName(String driverLicense, String name){
        return this.ownerPersistence.updateName(driverLicense,name);
    }

    public BigDecimal getTotalCostByDriverLicense(String driverLicense){
        return ownerPersistence.getTotalCostByDriverLicense(driverLicense);
    }
}
