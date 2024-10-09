package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.PieceEntity;
import es.upm.miw.apaw_practice.domain.models.car.Car;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Optional;



import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class CarRepositoryIT {

    @Autowired
    private CarRepository carRepository;

    @Test
    void testFindByModel() {

        Optional<CarEntity> carEntity = carRepository.findByModel("Mustang");
        assertTrue(carEntity.isPresent());
        assertFalse(carEntity.isEmpty());
        assertNotNull(carEntity.get());
        Car car = carEntity.get().toCar();
        assertEquals("Mustang", car.getModel());
        assertEquals(false, car.isElectric());
        assertEquals(new BigDecimal(50000), car.getPrice());

    }

    void testFindModelNotExisting(){
        assertTrue(this.carRepository.findByModel("QQQ").isEmpty());
    }
}
