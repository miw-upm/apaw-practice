package es.upm.miw.apaw_practice.domain.services.car_workshop;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.CarPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.OwnerPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.TyrePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class TyreService {

    private final TyrePersistence tyrePersistence;
    private final OwnerPersistence ownerPersistence;
    private final CarPersistence carPersistence;

    @Autowired
    private TyreService(TyrePersistence tyrePersistence, OwnerPersistence ownerPersistence, CarPersistence carPersistence) {
        this.tyrePersistence = tyrePersistence;
        this.ownerPersistence = ownerPersistence;
        this.carPersistence = carPersistence;
    }

    public void deleteManufacturer(String manufacturer) {
        this.tyrePersistence.deleteManufacturer(manufacturer);
    }

    public Stream<String> findModelByOwnerNameAndRevision(String ownerName, Boolean revision) {
        Owner owner = this.ownerPersistence.findByName(ownerName);
        Stream<Car> cars = this.carPersistence.findByOwnerAndRevision(owner, revision);
        return this.tyrePersistence.findDistinctModelByCar(cars);
    }
}
