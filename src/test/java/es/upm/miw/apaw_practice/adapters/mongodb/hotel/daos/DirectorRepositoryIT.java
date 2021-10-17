package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.DirectorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class DirectorRepositoryIT {
    @Autowired
    private DirectorRepository directorRepository;

    @Test
    void testFindByDni() {
        Optional<DirectorEntity> directorEntity = this.directorRepository.findByDni("77777777V");
        assertTrue(directorEntity.isPresent());
        assertEquals("test@email.com", directorEntity.get().getEmail());
        assertEquals(222222222, directorEntity.get().getTelephone());
        assertEquals(1, directorEntity.get().getHotelEntityList().size());
    }

}
