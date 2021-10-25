package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class OwnerPersistenceMongodbIT {

    @Autowired
    private OwnerPersistenceMongodb ownerPersistence;

    @Test
    void testGetDniFromCars() {
        Car carA = new Car("1111AAA", true, new Owner("00000000Z", "John Doe"), new ArrayList<>());
        Car carB = new Car("2222BBB", false, new Owner("00000000Z", "John Doe"), new ArrayList<>());
        Car carC = new Car("3333CCC", true, new Owner("99999999A", "Jane Doe"), new ArrayList<>());
        List<String> dnis = this.ownerPersistence.getDniFromCars(Stream.of(carA, carB, carC))
                .collect(Collectors.toList());
        assertEquals(2, dnis.size());
        assertTrue(dnis.containsAll(List.of("00000000Z", "99999999A")));
    }
}
