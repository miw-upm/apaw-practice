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
        Guest guest = Guest.builder()
                .nif("49376787L")
                .fullName("Ricardo Amaro")
                .birthDay(LocalDateTime.of(1990, 10, 27, 23, 2, 2))
                .build();
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
        Guest guest = Guest.builder()
                .nif("00914833P")
                .fullName("Juan Carpeto")
                .birthDay(LocalDateTime.of(1990, 10, 27, 23, 2, 2))
                .build();
        this.guestService.create(guest);
        this.guestService.delete("00914833P");
        assertThrows(NotFoundException.class, () -> this.guestService.read("00914833P"));
    }
}
