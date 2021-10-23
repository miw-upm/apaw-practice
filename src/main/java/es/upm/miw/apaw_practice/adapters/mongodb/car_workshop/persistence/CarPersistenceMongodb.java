package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.CarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.OwnerRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.OwnerEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.TyreSpecificationEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecification;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.CarPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("carPersistence")
public class CarPersistenceMongodb implements CarPersistence {

    private final CarRepository carRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public CarPersistenceMongodb(CarRepository carRepository, OwnerRepository ownerRepository) {
        this.carRepository = carRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void create(Car car) {
        this.carRepository
                .save(new CarEntity(car));
    }

    @Override
    public boolean existLicensePlate(String licensePlate) {
        return this.carRepository
                .findByLicensePlate(licensePlate)
                .isPresent();
    }

    @Override
    public void updateOwner(String licensePlate, Owner owner) {
        CarEntity car = this.carRepository
                .findByLicensePlate(licensePlate)
                .orElseThrow(() -> new NotFoundException("LicensePlate: " + licensePlate));
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.fromOwner(owner);
        car.setOwner(ownerEntity);
        this.carRepository.save(car);
    }

    @Override
    public Stream<Car> findByOwnerAndRevision(OwnerEntity owner, Boolean revision) {
        return this.carRepository.findByOwner(owner).stream()
                .filter(car -> car.isRevision() == revision)
                .map(CarEntity::toCar);
    }

    @Override
    public Car findByLicensePlate(String licensePlate) {
        CarEntity carEntity = this.carRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new NotFoundException("Car not found: " + licensePlate));
        Car car = carEntity.toCar();
        return car;
    }

    @Override
    public Stream<Car> findByTyreSpecifications(Stream<TyreSpecification> tyreSpecs) {
        List<TyreSpecification> tyreSpecificationList = tyreSpecs.collect(Collectors.toList());
        List<Car> cars = new ArrayList<>();
        for (TyreSpecification tyreSpecification : tyreSpecificationList) {
            cars.addAll(this.carRepository.findAll().stream()
                    .filter(carEntity -> carEntity.getTyreSpecsEntities().stream()
                            .map(TyreSpecificationEntity::toTyreSpecification)
                            .anyMatch(tyreSpec -> tyreSpec.equals(tyreSpecification))
                    )
                    .distinct().map(CarEntity::toCar)
                    .collect(Collectors.toList())
            );
        }
        return cars.stream().distinct();
    }
}
