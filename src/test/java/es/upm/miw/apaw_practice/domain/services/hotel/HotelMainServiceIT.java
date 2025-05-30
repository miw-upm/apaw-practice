package es.upm.miw.apaw_practice.domain.services.hotel;


import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelMain;
import es.upm.miw.apaw_practice.domain.models.hotel.HotelRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
public class HotelMainServiceIT {

    @Autowired
    private HotelMainService hotelMainService;

    @Test
    void findByName() {
        HotelMain hotel = this.hotelMainService.findByName("xiangHotel");
        assertEquals("966666666", hotel.getPhone());
    }

    @Test
    void delete(){
        this.hotelMainService.delete("mengfeiHotel");
        assertThrows(NotFoundException.class, () -> this.hotelMainService.findByName("mengfeiHotel"));
    }
    @Test
    void updateRoom() {
        HotelMain hotelMain = this.hotelMainService.findByName("xiangHotel");
        String roomNumber = "101";
        HotelRoom room = new HotelRoom("101", "doble", new BigDecimal("40.00"), false);
        HotelMain hotelUpdatedRoom = this.hotelMainService.updateRoom("xiangHotel","101", room);
        HotelRoom updatedRoom = hotelUpdatedRoom.getRooms()
                .stream()
                .filter(roomToFind -> room.getNumber().equals(roomNumber))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Room number not found: " + roomNumber));
        assertEquals("xiangHotel",hotelMain.getName());
        assertEquals("101",updatedRoom.getNumber());
        assertEquals("doble",updatedRoom.getType());
        assertTrue(updatedRoom.getPrice().compareTo(new BigDecimal("40.00")) == 0);
        assertFalse(updatedRoom.isReserved());
    }

    @Test
    void testFindNonRepeatedRoomNumberByType() {
        String type = "dual";
        List<String> roomNumberList = this.hotelMainService.findNonRepeatedRoomNumberByType(type).collect(Collectors.toList());
        assertNotNull(roomNumberList);
        assertTrue(roomNumberList.containsAll(Arrays.asList("202","303")));
    }

}
