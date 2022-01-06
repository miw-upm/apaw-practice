package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Car;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Owner;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Tyre;
import es.upm.miw.apaw_practice.domain.models.car_workshop.TyreSpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class TyrePersistenceMongodbIT {

    @Autowired
    private TyrePersistenceMongodb tyrePersistence;

    @Test
    void testCreateAndRead() {
        Tyre tyre = Tyre.builder().manufacturer("Firestone").model("Champion").price(new BigDecimal("119.99")).build();
        this.tyrePersistence.create(tyre);
        Tyre tyreBBDD = this.tyrePersistence.read("Champion");
        assertEquals("Firestone", tyreBBDD.getManufacturer());
        assertEquals(0, new BigDecimal("119.99").compareTo(tyreBBDD.getPrice()));
    }

    @Test
    void testCreateAndDeleteByManufacturer() {
        Tyre tyre = Tyre.builder().manufacturer("Firestone").model("Roadhawk").price(new BigDecimal("99.99")).build();
        this.tyrePersistence.create(tyre);
        this.tyrePersistence.deleteManufacturer("Firestone");
        assertThrows(NotFoundException.class, () -> this.tyrePersistence.read("Roadhawk"));
    }

    //@Test
    void testFindDistinctModelByCar() {
        Tyre tyreA = this.tyrePersistence.read("Ventus Prime");
        Tyre tyreB = this.tyrePersistence.read("Kinergy");
        Tyre tyreC = this.tyrePersistence.read("Primacy");
        TyreSpecification tyreSpecA = new TyreSpecification(205, 16, "90T", List.of(tyreA, tyreB));
        TyreSpecification tyreSpecB = new TyreSpecification(215, 16, "85V", List.of(tyreB, tyreC));
        Car car = new Car("1234ABC", true, new Owner("00000000Z", "John Doe"), List.of(tyreSpecA, tyreSpecB));
        List<String> models = this.tyrePersistence.findDistinctModelByCar(Stream.of(car))
                .collect(Collectors.toList());
        assertTrue(models.containsAll(List.of("Ventus Prime", "Kinergy", "Primacy")));
        assertEquals(3, models.size());
    }

}
