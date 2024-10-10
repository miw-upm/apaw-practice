package es.upm.miw.apaw_practice.domain.services.car;

import es.upm.miw.apaw_practice.domain.models.car.Car;
import es.upm.miw.apaw_practice.domain.persistence_ports.car.CarPersistence;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Service
public class CarService {

    private final CarPersistence carPersistence;

    public CarService(CarPersistence carPersistence) {
        this.carPersistence = carPersistence;
    }

    public Stream<Car> readAll(){
        return this.carPersistence.readAll();
    }

    public void create(Car car) {
        carPersistence.create(car);
    }

    public void delete(String model) {
        carPersistence.delete(model);
    }

    public BigDecimal getTotalCostByDriverLicense(String driverLicense){
        return carPersistence.getTotalCostByDriverLicense(driverLicense);
    }
}
