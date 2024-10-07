package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class RoomPersistenceMongodbIT {

    @Autowired
    private RoomPersistenceMongodb roomPersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.roomPersistenceMongodb.read("0"));
    }

    @Test
    void testRoomExists() {
        assertNotNull(this.roomPersistenceMongodb.read("101"));
    }

    @Test
    void testCreateAndRead() {
        Room room = new Room("301", false, 4, BigDecimal.valueOf(99.99), new ArrayList<>());
        Room createdRoom = this.roomPersistenceMongodb.create(room);
        assertEquals("301", createdRoom.getNum());
        assertFalse(createdRoom.getOccupied());
        assertEquals(4, createdRoom.getNumBeds());
        assertEquals(BigDecimal.valueOf(99.99), createdRoom.getPrice());
        assertEquals(0, createdRoom.getBookings().size());
    }
}
