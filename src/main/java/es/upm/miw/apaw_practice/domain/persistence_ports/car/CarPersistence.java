package es.upm.miw.apaw_practice.domain.persistence_ports.car;


import es.upm.miw.apaw_practice.domain.models.car.Car;
import org.springframework.stereotype.Repository;




@Repository
public interface CarPersistence {


    Car readByModel(String model);

    Car create(Car car);

    void delete(String model);


}
