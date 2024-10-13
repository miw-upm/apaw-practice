package es.upm.miw.apaw_practice.adapters.mongodb.movies.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.movies.entities.ActorEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

    @Test
    void testEqualsAndHashCode() {
        assertTrue(this.actorRepository.findByArtisticName("Robert De Niro").isPresent());
        ActorEntity actor1 = this.actorRepository.findByArtisticName("Robert De Niro").get();

        assertTrue(this.actorRepository.findByArtisticName("Robert De Niro").isPresent());
        ActorEntity actor2 = this.actorRepository.findByArtisticName("Robert De Niro").get();

        assertEquals(actor1, actor2);
        assertEquals(actor1.hashCode(), actor2.hashCode());

        assertTrue(this.actorRepository.findByArtisticName("Tom Hardy").isPresent());
        ActorEntity actor3 = this.actorRepository.findByArtisticName("Tom Hardy").get();

        assertNotEquals(actor1, actor3);
    }

    @Test
    void testToString() {
        assertTrue(this.actorRepository.findByArtisticName("Scarlett Johansson").isPresent());
        ActorEntity actor = this.actorRepository.findByArtisticName("Scarlett Johansson").get();
        String expectedString = """
                ActorEntity {
                  artisticName: "Scarlett Johansson",
                  realName: "Scarlett Ingrid Johansson",
                  isAvailable: true,
                  birthDate: "1984-11-22"
                }""";
        assertEquals(expectedString, actor.toString());
    }
}
