package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.CarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.OwnerCarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.OwnerCarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.PieceEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.CarPersistence;
import es.upm.miw.apaw_practice.domain.models.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Stream;

@Repository("carPersistence")
public class CarPersistenceMongodb implements CarPersistence {

    private CarRepository carRepository;

    private OwnerCarRepository ownerCarRepository;

    @Autowired
    public CarPersistenceMongodb(CarRepository carRepository, OwnerCarRepository ownerCarRepository){
        this.carRepository = carRepository;
        this.ownerCarRepository = ownerCarRepository;
    }

    @Override
    public Stream<Car> readAll() {
        return carRepository
                .findAll()
                .stream()
                .map(CarEntity::toCar);
    }

    @Override
    public Car readByModel(String model){
        return carRepository
                .findByModel(model)
                .orElseThrow(() -> new NotFoundException("Car model: " + model))
                .toCar();
    }

    @Override
    public Car create(Car car) {
        return carRepository
                .save(new CarEntity(car))
                .toCar();
    }

    @Override
    public void delete(String model) {
        Optional<CarEntity> carToDelete = carRepository.findByModel(model);
        carToDelete.ifPresent(carRepository::delete);
    }

    @Override
    public BigDecimal getTotalCostByDriverLicense(String driverLicense) {
        OwnerCarEntity ownerCarEntity = this.ownerCarRepository
                .findByDriverLicense(driverLicense)
                .orElseThrow(() -> new NotFoundException("Owner Car with driverLicense " + driverLicense + " not found"));

        return this.carRepository.findAll()
                .stream()
                .filter(car -> car.getOwnerEntity() != null
                && car.getOwnerEntity().getDriverLicense().equals(ownerCarEntity.getDriverLicense()))
                .flatMap(car -> car.getPiecesEntity().stream())
                .map(PieceEntity::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
