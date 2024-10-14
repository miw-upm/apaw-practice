package es.upm.miw.apaw_practice.domain.models.theme_park;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class UserTest {

    @Test
    void testBuilder() {
        User instance = User.builder()
                        .idMembership("POQWRJ32K")
                        .address("C/ Casa,23")
                        .entranceDate(LocalDateTime.of(1995,7,8,12,5,2))
                        .oneYearMembership(false)
                        .build();
        assertEquals("POQWRJ32K", instance.getIdMembership());
        assertEquals("C/ Casa,23", instance.getAddress());
        assertEquals(LocalDateTime.of(1995,7,8,12,5,2), instance.getEntranceDate());
        assertFalse(instance.getOneYearMembership());
    }
}
