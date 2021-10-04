package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.RoomEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.daos.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class RoomRepositoryIT {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testCreateAndRead() {
        assertTrue(this.roomRepository.findAll().stream()
                .anyMatch(room -> 22 == room.getNumberRoom() &&
                        BigDecimal.valueOf(45).equals(room.getPriceRoom()) &&
                        !room.isVip() &&
                        2 == room.getHotelGuestEntities().size()));

    }

    @Test
    public void testFindByRoomNumber() {
        assertTrue(this.roomRepository.findByNumberRoom(45).isPresent());
        RoomEntity room = this.roomRepository.findByNumberRoom(45).get();
        assertEquals(BigDecimal.valueOf(120), room.getPriceRoom());
        assertEquals("88888888K", room.getHotelGuestsEntities().get(0).getDniGuest());
        assertEquals(1, room.getHotelGuestEntities().size());
    }
}
