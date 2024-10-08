package es.upm.miw.apaw_practice.domain.services.hotel_retired;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestConfig
public class GuestServiceIT {

    @Autowired
    private GuestService guestService;

    @Test
    void testCreateAndRead() {
        Guest guest = new Guest(
                "49376787L",
                "Ricardo Amaro",
                LocalDateTime.of(1990, 10, 27, 23, 2, 2)
        );
        Guest createdGuest = this.guestService.create(guest);
        assertEquals("49376787L", createdGuest.getNif());
        assertEquals("Ricardo Amaro", createdGuest.getFullName());
        assertEquals(LocalDateTime.of(1990, 10, 27, 23, 2, 2), createdGuest.getBirthDay());
    }
}
