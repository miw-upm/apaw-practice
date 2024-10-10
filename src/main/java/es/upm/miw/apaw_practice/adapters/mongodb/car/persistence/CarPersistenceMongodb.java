package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.CarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.OwnerCarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.CarEntity;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.CarPersistence;
import es.upm.miw.apaw_practice.domain.models.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;

import java.util.Optional;

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


}
