package es.upm.miw.apaw_practice.domain.services.theme_park;

import es.upm.miw.apaw_practice.TestConfig;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import es.upm.miw.apaw_practice.domain.persistence_ports.theme_park.UserPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class UserServiceIT {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPersistence userPersistence;

    @Test
    void testCreate() {
        User user = new User("001", "C/Rueda, 1", LocalDateTime.of(2007,12,12,3,20), true);
        User serviceUser = this.userService.create(user);
        assertEquals("001", serviceUser.getIdMembership());
        assertEquals("C/Rueda, 1", serviceUser.getAddress());
        assertEquals(LocalDateTime.of(2007,12,12,3,20), serviceUser.getEntranceDate());
        assertTrue(serviceUser.getOneYearMembership());
    }

    @Test
    void testCreateConflict(){
        User user = new User("1252", "C/Rueda, 1", LocalDateTime.of(2007,12,12,3,20), false);
        assertThrowsExactly(ConflictException.class, () -> this.userService.create(user));
    }

}
