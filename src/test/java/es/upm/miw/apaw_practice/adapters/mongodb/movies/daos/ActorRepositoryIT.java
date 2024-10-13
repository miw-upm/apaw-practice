package es.upm.miw.apaw_practice.adapters.mongodb.movies.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.ActorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestConfig
class ActorRepositoryIT {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void testFindByArtisticNameNotExists() {
        assertTrue(actorRepository.findByArtisticName("Alfredo Landa").isEmpty());
    }

    @Test
    void testFindByArtisticName() {
        assertTrue(this.actorRepository.findByArtisticName("Jack Nicholson").isPresent());
        ActorEntity actor = this.actorRepository.findByArtisticName("Jack Nicholson").get();
        assertEquals("Jack Nicholson", actor.getArtisticName());
        assertEquals("John Joseph Nicholson", actor.getRealName());
        assertFalse(actor.getAvailable());
        assertEquals(LocalDate.of(1937, 4, 22), actor.getBirthDate());
    }
}
