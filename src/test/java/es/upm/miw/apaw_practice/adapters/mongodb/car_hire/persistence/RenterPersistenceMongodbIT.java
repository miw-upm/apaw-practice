package es.upm.miw.apaw_practice.adapters.mongodb.car_hire.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.car_hire.Renter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class RenterPersistenceMongodbIT {

    @Autowired
    RenterPersistenceMongodb renterPersistenceMongodb;

    @Test
    void testReadByDniNotFound() {
        assertThrows(NotFoundException.class, () -> this.renterPersistenceMongodb.readByDni("00000000"));
    }

    @Test
    void testCreateAndReadByDni() {
        Renter renter = new Renter("Pepito", "987654");
        assertNull(renter.getLikedCar());
        this.renterPersistenceMongodb.create(renter);
        assertEquals("987654", this.renterPersistenceMongodb.readByDni("987654").getDni());
        assertEquals("Pepito", this.renterPersistenceMongodb.readByDni("987654").getName());
        assertNull(this.renterPersistenceMongodb.readByDni("987654").getLikedCar());
    }

    @Test
    void testUpdate() {
        Renter renter = new Renter("Ok", "123");
        assertNull(renter.getLikedCar());
        this.renterPersistenceMongodb.create(renter);
        renter.setLikedCar(Boolean.TRUE);
        this.renterPersistenceMongodb.update("123", renter);
        renter = this.renterPersistenceMongodb.readByDni("123");
        assertTrue(renter.getLikedCar());
    }
}
