package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class CarRepositoryIT {

    @Autowired
    private CarRepository carRepository;

    @Test
    void testFindByModel() {
        assertTrue(this.carRepository.findByModel("Mustang").isPresent());
    }
}
