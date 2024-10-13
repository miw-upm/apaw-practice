package es.upm.miw.apaw_practice.adapters.mongodb.theme_park.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.theme_park.ThemeParkSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.theme_park.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class UserPersistenceMongodbIT {

    @Autowired
    private UserPersistenceMongodb userPersistence;

    @Autowired
    private ThemeParkSeederService themeParkSeederService;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.userPersistence.read("0"));
    }

    @Test
    void testBarcodeNotExist() {
        assertFalse(this.userPersistence.existIdMembership("0"));
    }

    @Test
    void testBarcodeExist() {
        assertTrue(this.userPersistence.existIdMembership("14674"));
    }

    @Test
    void testCreateAndRead() {
        User user =
                new User("123456789", "C/Preciados, 5", LocalDateTime.of(2000,6,3,0,0), true);
        this.userPersistence.create(user);
        User userBD = this.userPersistence.read("123456789");
        assertEquals("C/Preciados, 5", userBD.getAddress());
        assertEquals(LocalDateTime.of(2000,6,3,0,0), userBD.getEntranceDate());
        assertTrue(userBD.getOneYearMembership());
    }

    @Test
    void testUpdate(){
        Optional<User> userSelected = this.userPersistence.readAll()
                .filter(users -> "1252".equals(users.getIdMembership()))
                .findFirst();
        assertTrue(userSelected.isPresent());

        userSelected.get().setAddress("new Address");
        userSelected.get().setEntranceDate(LocalDateTime.of(2009,12,21,2,2,5));
        userSelected.get().setOneYearMembership(false);

        this.userPersistence.update("1252", userSelected.get());
        Optional<User> newUser = this.userPersistence.readAll()
                .filter(users -> "1252".equals(users.getIdMembership()))
                .findFirst();
        assertTrue(newUser.isPresent());

        assertEquals("new Address", newUser.get().getAddress());
        assertEquals(LocalDateTime.of(2009,12,21,2,2,5), newUser.get().getEntranceDate());
        assertFalse(newUser.get().getOneYearMembership());
        themeParkSeederService.deleteAll();
        themeParkSeederService.seedDatabase();
    }
    
}
