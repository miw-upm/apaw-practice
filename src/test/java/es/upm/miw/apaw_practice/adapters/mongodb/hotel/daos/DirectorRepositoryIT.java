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
        Optional<DirectorEntity> director = this.directorRepository.findByDni("77777777V");
       assertTrue(director.isPresent());
       assertEquals("test@email.com", director.get().getEmail());
       assertEquals(222222222, director.get().getTelephone());
    }
}
