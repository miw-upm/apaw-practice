package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.OwnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.OwnerPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("ownerPersistence")
public class OwnerPersistenceMongodb implements OwnerPersistence {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerPersistenceMongodb(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner readByDni(String dni) {
        return this.ownerRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException("Owner dni: " + dni))
                .toOwner();
    }

    @Override
    public OwnerEntity findByName(String name) {
        return this.ownerRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Owner name: " + name));
    }

    @Override
    public Stream<String> getDniFromCars(Stream<Car> cars) {
        return cars.map(Car::getOwner)
                .distinct()
                .map(Owner::getDni);
    }

}
