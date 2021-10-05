package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class HotelRepositoryIT {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    void testCreateAndRead() {
        assertTrue(this.hotelRepository.findAll().stream()
                .anyMatch(hotel -> "Av. Madrid, Madrid, 32452".equals(hotel.getDirection()) &&
                        3 == hotel.getNumberStars() &&
                        "77777777V".equals(hotel.getDirector().getDniDirector()) &&
                        1 == hotel.getRooms().size()
                ));
    }


}
