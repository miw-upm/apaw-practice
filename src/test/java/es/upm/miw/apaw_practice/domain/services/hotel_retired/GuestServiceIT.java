package es.upm.miw.apaw_practice.domain.services.hotel_retired;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Hotel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void testRead() {
        Guest guest = this.guestService.read("99527370E");
        assertEquals("99527370E", guest.getNif());
    }

    @Test
    void testDelete() {
        Guest guest = new Guest(
                "00914833P",
                "Juan Carpeto",
                LocalDateTime.of(1990, 10, 27, 23, 2, 2)
        );
        this.guestService.create(guest);
        this.guestService.delete("00914833P");
        assertThrows(NotFoundException.class, () -> this.guestService.read("00914833P"));
    }
}
