package es.upm.miw.apaw_practice.adapters.mongodb.car.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.CarEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.car.entities.PieceEntity;
import java.util.stream.Collectors;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class CarRepositoryIT {

    @Autowired
    private CarRepository carRepository;

    @Test
    void testFindByModel() {
        assertTrue(this.carRepository.findByModel("Mustang").isPresent());
    }

    @Test
    void testCreateAndRead() {
        assertTrue(this.carRepository.findByModel("Mustang").isPresent());
        CarEntity car = this.carRepository.findByModel("Mustang").get();
        assertEquals("Mustang", car.getModel());
        assertEquals(false, car.isElectric());
        assertEquals("50000", car.getPrice().toString());
        assertTrue(car.getPiecesEntity().stream()
                .map(PieceEntity::getPartNumber)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList("SFGE"))); // El código de la pieza asociada al Mustang
    }
}
