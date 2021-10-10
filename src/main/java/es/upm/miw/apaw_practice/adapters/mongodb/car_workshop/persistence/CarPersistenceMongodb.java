package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.daos.CarRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.entities.CarEntity;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop.CarPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("carPersistence")
public class CarPersistenceMongodb implements CarPersistence {

    private final CarRepository carRepository;

    @Autowired
    public CarPersistenceMongodb(CarRepository carRepository) {
        this.carRepository = carRepository;
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
}
