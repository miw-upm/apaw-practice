package es.upm.miw.apaw_practice.domain.services.hotel_retired;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
