package es.upm.miw.apaw_practice.adapters.mongodb.hotel.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel.entities.DirectorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class DirectorRepositoryIT {
    @Autowired
    private DirectorRepository directorRepository;


    @Test
    void testFindByDni() {
        assertTrue(this.directorRepository.findByDniDirector("77777777V").isPresent());
        DirectorEntity director = this.directorRepository.findByDniDirector("77777777V").get();
        assertEquals("test@email.com", director.getEmail());
        assertEquals(222222222, director.getTelephone());
    }
}
