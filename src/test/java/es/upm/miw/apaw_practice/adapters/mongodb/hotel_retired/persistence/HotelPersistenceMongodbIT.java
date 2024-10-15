package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class HotelPersistenceMongodbIT {

    @Autowired
    private HotelPersistenceMongodb hotelPersistenceMongodb;

    @Autowired
    private RoomPersistenceMongodb roomPersistenceMongodb;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.hotelPersistenceMongodb.read("0"));
    }

    @Test
    void testCifNotExists() {
        assertFalse(this.hotelPersistenceMongodb.existCIF("0"));
    }

    @Test
    void testCifExists() {
        assertTrue(this.hotelPersistenceMongodb.existCIF("F91635847"));
    }

    @Test
    void testCreateAndRead() {
        List<Room> rooms = this.roomPersistenceMongodb.readAll().toList();
        Hotel hotel = new Hotel("S1726325B", "Foo hotel", "Foo address", rooms);
        this.hotelPersistenceMongodb.create(hotel);
        Hotel createdHotel = this.hotelPersistenceMongodb.read("S1726325B");
        assertEquals("S1726325B", createdHotel.getCif());
        assertEquals("Foo hotel", createdHotel.getHotelName());
        assertEquals("Foo address", createdHotel.getAddress());
    }

    @Test
    void testCreteAndUpdate() {
        List<Room> rooms = this.roomPersistenceMongodb.readAll().toList();
        Hotel hotel = new Hotel("J37512043", "Foo hotel", "Foo address", rooms);
        this.hotelPersistenceMongodb.create(hotel);
        Hotel createdHotel = this.hotelPersistenceMongodb.read("J37512043");
        assertEquals("J37512043", createdHotel.getCif());
        createdHotel.setHotelName("Bar hotel");
        Hotel updatedHotel = this.hotelPersistenceMongodb.update("J37512043", createdHotel);
        assertEquals("Bar hotel", updatedHotel.getHotelName());
    }

    @Test
    void testFindByArtistNameSumPricesExhibitions() {
        assertEquals(BigDecimal.valueOf(319.95), this.hotelPersistenceMongodb.findTotalSumOfPrice("LaMaria", "Emilio Pedrajas"));
    }

    @Test
    void testFindNonDuplicatedHotelNamesByNumBedsAndNumBookings() {
        Stream<String> hotelNames= this.hotelPersistenceMongodb.findNonDuplicatedHotelNamesByNumBedsAndNumBookings(2, 3);

        assertEquals(List.of("LaMaria"), hotelNames.toList());
    }
}
