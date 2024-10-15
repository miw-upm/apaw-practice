package es.upm.miw.apaw_practice.domain.services.car;

import es.upm.miw.apaw_practice.domain.models.car.Car;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.CarPersistence;
import org.springframework.stereotype.Service;



@Service
public class CarService {

    private final CarPersistence carPersistence;

    public CarService(CarPersistence carPersistence) {
        this.carPersistence = carPersistence;
    }


    public void create(Car car) {
        carPersistence.create(car);
    }

    public void delete(String model) {
        carPersistence.delete(model);
    }


}
