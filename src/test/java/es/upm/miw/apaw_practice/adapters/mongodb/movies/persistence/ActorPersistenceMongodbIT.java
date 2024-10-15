package es.upm.miw.apaw_practice.adapters.mongodb.movies.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.MoviesSeederService;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.movies.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class ActorPersistenceMongodbIT {

    @Autowired
    private ActorPersistenceMongodb actorPersistence;

    @Autowired
    private MoviesSeederService moviesSeederService;

    @Test
    void testFindByArtisticNameNotFound() {
        assertThrows(NotFoundException.class, () -> this.actorPersistence.findByArtisticName("Jennifer Aniston"));
    }

    @Test
    void testFindByArtisticName() {
        Actor actor = this.actorPersistence.findByArtisticName("Natalie Portman");
        assertEquals("Natalie Portman", actor.getArtisticName());
        assertEquals("Natalie Hershlag", actor.getRealName());
        assertTrue(actor.isAvailable());
        assertEquals(LocalDate.of(1981, 6, 9), actor.getBirthDate());
    }

    @Test
    void testUpdate() {
        Actor actor = this.actorPersistence.findByArtisticName("Samuel L. Jackson");
        actor.setIsAvailable(false);
        actor.setRealName("Samuel Leeroy Jenkins");
        this.actorPersistence.update(actor);

        Actor updatedActor = this.actorPersistence.findByArtisticName("Samuel L. Jackson");
        assertEquals("Samuel Leeroy Jenkins", updatedActor.getRealName());
        assertFalse(updatedActor.isAvailable());
    }

}
