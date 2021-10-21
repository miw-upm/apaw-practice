package es.upm.miw.apaw_practice.adapters.mongodb.hotel.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel.Hotel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class HotelPersistenceMongodbIT {
    @Autowired
    private HotelPersistenceMongodb hotelPersistenceMongodb;

    @Test
    void testFindHotelById() {
        assertEquals(3, this.hotelPersistenceMongodb.read("1").getRooms().get(0).getNumber());
        assertEquals("Av. Luto, 23981", this.hotelPersistenceMongodb.read("1").getDirection());
    }

    @Test
    void testUpdate() {
        Optional<Hotel> hotel = Optional.ofNullable(this.hotelPersistenceMongodb.read("2"));
        assertTrue(hotel.isPresent());
        assertEquals("Av. Salamanca, Salamanca, 15243", hotel.get().getDirection());
        assertEquals(4, hotel.get().getNumberStars());

        Hotel hotelParams = new Hotel("Lora", "updatedDirection", 2, null);
        this.hotelPersistenceMongodb.update("2", hotelParams);

        Optional<Hotel> updatedHotel = Optional.ofNullable(this.hotelPersistenceMongodb.read("2"));
        assertTrue(updatedHotel.isPresent());

        assertEquals("updatedDirection", updatedHotel.get().getDirection());
        assertEquals(2, updatedHotel.get().getNumberStars());
    }

    @Test
    void testFindHotelNameListByGuestName() {
        List<Hotel> search = this.hotelPersistenceMongodb.findHotelNameListByGuestName("Mario");
        assertEquals(2, search.size());
        List<String> hotelNames = List.of("MariaLuisa", "Gran hotel");
        assertEquals(hotelNames, search.stream().map(Hotel::getName).collect(Collectors.toList()));
    }
}
