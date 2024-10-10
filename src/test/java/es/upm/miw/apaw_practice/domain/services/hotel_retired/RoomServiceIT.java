package es.upm.miw.apaw_practice.domain.services.hotel_retired;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestConfig
public class RoomServiceIT {

    @Autowired
    private RoomService roomService;

    @Test
    void testCreateAndRead() {
        Room room = new Room("401", false, 4, BigDecimal.valueOf(99.99), Collections.emptyList());
        Room createdRoom = this.roomService.create(room);
        assertEquals("401", createdRoom.getNum());
    }

    @Test
    void testRead() {
        Room room = this.roomService.read("101");
        assertNotNull(room);
    }

    @Test
    void testDelete() {
        Room room = new Room("3467", false, 4, BigDecimal.valueOf(99.99), Collections.emptyList());
        Room createdRoom = this.roomService.create(room);
        assertNotNull(room);
        this.roomService.delete("3467");
    }
}
