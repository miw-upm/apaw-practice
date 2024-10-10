package es.upm.miw.apaw_practice.domain.services.hotel_retired;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
public class HotelServiceIT {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @Test
    void testCreateAndRead() {
        List<Room> rooms = this.roomService.search();

        Hotel hotel = new Hotel("W1674170D", "Bonavista", "C/ Malvarrosa 35 Bilbao", rooms);
        Hotel createdHotel = this.hotelService.create(hotel);
        assertEquals("W1674170D", createdHotel.getCif());
        assertEquals("Bonavista", createdHotel.getHotelName());
    }

    @Test
    void testRead() {
        Hotel hotel = this.hotelService.read("F91635847");
        assertEquals("F91635847", hotel.getCif());
    }

    @Test
    void testDelete() {
        List<Room> rooms = this.roomService.search();

        Hotel hotel = new Hotel("F57682445", "Bonavista", "C/ Malvarrosa 35 Bilbao", rooms);
        this.hotelService.create(hotel);

        this.hotelService.delete("F57682445");
        assertThrows(NotFoundException.class, () -> this.hotelService.read("F57682445"));
    }
}
