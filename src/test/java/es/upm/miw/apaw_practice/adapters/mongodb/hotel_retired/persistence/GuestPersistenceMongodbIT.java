package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class GuestPersistenceMongodbIT {

    @Autowired
    private GuestPersistenceMongodb guestPersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.guestPersistenceMongodb.read("0"));
    }

    @Test
    void testNifNotExists() {
        assertFalse(this.guestPersistenceMongodb.existsNIF("0"));
    }

    @Test
    void testNifExists() {
        assertTrue(this.guestPersistenceMongodb.existsNIF("99527370E"));
    }

    @Test
    void testCreateAndRead() {
        Guest guest = new Guest(
                "63376389G", "Ricardo Pérez",
                LocalDateTime.of(1990, 10, 27,  23, 2, 2 )
        );
        this.guestPersistenceMongodb.create(guest);
        Guest createdGuest = this.guestPersistenceMongodb.read("63376389G");
        assertEquals("63376389G", createdGuest.getNif());;
        assertEquals("Ricardo Pérez", createdGuest.getFullName());
        assertEquals(LocalDateTime.of(1990, 10, 27,  23, 2, 2 ), createdGuest.getBirthDay());
    }

    @Test
    void testCreateAndUpdate() {
        Guest guest = new Guest(
                "66802285G", "Ricardo Pérez",
                LocalDateTime.of(1990, 10, 27,  23, 2, 2 )
        );
        this.guestPersistenceMongodb.create(guest);
        Guest createdGuest = this.guestPersistenceMongodb.read("66802285G");
        assertEquals("Ricardo Pérez", createdGuest.getFullName());
        createdGuest.setFullName("Ricardo Gómez");
        Guest updatedGuest = this.guestPersistenceMongodb.update("66802285G", createdGuest);
        assertEquals("Ricardo Gómez", updatedGuest.getFullName());
    }

}
