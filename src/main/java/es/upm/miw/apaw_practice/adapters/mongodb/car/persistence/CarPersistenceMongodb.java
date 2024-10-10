package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car.daos.CarRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.CarPersistence;
import es.upm.miw.apaw_practice.domain.models.car.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;

@Repository("carPersistence")
public class CarPersistenceMongodb implements CarPersistence {

    private CarRepository carRepository;

    @Autowired
    public CarPersistenceMongodb(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public Car readByModel(String model){
        return carRepository
                .findByModel(model)
                .orElseThrow(() -> new NotFoundException("Car model: " + model))
                .toCar();
    }
}
