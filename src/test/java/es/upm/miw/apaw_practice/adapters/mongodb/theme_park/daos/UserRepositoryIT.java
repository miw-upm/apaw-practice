package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class UserRepositoryIT {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByIdMembership() {
        assertTrue(this.userRepository.findByIdMembership("14674").isPresent());
        UserEntity user = this.userRepository.findByIdMembership("14674").get();
        assertEquals("C/Gamepolis, 2", user.getAddress());
        assertEquals(LocalDateTime.of(2021,6,4,10,6) ,user.getEntranceDate());
        assertFalse(user.getOneYearMembership());
    }

}
