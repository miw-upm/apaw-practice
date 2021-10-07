package es.upm.miw.apaw_practice.adapters.mongodb.car_workshop.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_workshop.Tyre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class TyrePersistenceMongodbIT {

    @Autowired
    private TyrePersistenceMongodb tyrePersistence;

    @Test
    void testCreateAndRead() {
        Tyre tyre = new Tyre("Firestone", "Champion", new BigDecimal("119.99"));
        this.tyrePersistence.create(tyre);
        Tyre tyreBBDD = this.tyrePersistence.read("Champion");
        assertEquals("Firestone", tyreBBDD.getManufacturer());
        assertEquals(0, new BigDecimal("119.99").compareTo(tyreBBDD.getPrice()));
    }

    @Test
    void testCreateAndDeleteByManufacturer() {
        Tyre tyre = new Tyre("Firestone", "Roadhawk", new BigDecimal("99.99"));
        this.tyrePersistence.create(tyre);
        this.tyrePersistence.deleteManufacturer("Firestone");
        assertThrows(NotFoundException.class, () -> this.tyrePersistence.read("Roadhawk"));
    }

}
