package es.upm.miw.apaw_practice.adapters.mongodb.car.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.car.Car;
import es.upm.miw.apaw_practice.domain.models.car.Piece;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class CarPersistenceMongodbIT {

    @Autowired
    private CarPersistenceMongodb carPersistence;

    @Test
    public void testReadByModel(){
        Car car = this.carPersistence.readByModel("Model 3");
        assertEquals("Model 3", car.getModel());
        assertEquals(true, car.isElectric());
        assertEquals(new BigDecimal(40000), car.getPrice());
        assertTrue(car.getPieces()
                .stream()
                .map(Piece::getPartNumber)
                .collect(Collectors.toList())
                .contains("WSDF"));
        assertTrue(car.getOwner().getName().equals("Marcos"));

    }
}
