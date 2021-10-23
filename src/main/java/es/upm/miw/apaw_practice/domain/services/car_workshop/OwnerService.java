package es.upm.miw.apaw_practice.domain.services.car_workshop;

import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecification;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.CarPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.OwnerPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.TyreSpecificationPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OwnerService {

    private final OwnerPersistence ownerPersistence;
    private final TyreSpecificationPersistence tyreSpecsPersistence;
    private final CarPersistence carPersistence;

    @Autowired
    public OwnerService(OwnerPersistence ownerPersistence, TyreSpecificationPersistence tyreSpecsPersistence, CarPersistence carPersistence) {
        this.ownerPersistence = ownerPersistence;
        this.tyreSpecsPersistence = tyreSpecsPersistence;
        this.carPersistence = carPersistence;
    }

    public Owner readByDni(String dni) {
        return this.ownerPersistence.readByDni(dni);
    }

    public Stream<String> findByTyreSpecificationDiameterGreaterThan(int diameter) {
        Stream<TyreSpecification> tyreSpecs = this.tyreSpecsPersistence.findByDiameterGreaterThan(diameter);
        Stream<Car> cars = this.carPersistence.findByTyreSpecifications(tyreSpecs);
        return this.ownerPersistence.getDniFromCars(cars);
    }
}
