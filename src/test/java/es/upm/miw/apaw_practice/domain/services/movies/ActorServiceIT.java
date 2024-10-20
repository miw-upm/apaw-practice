package es.upm.miw.apaw_practice.domain.services.movies;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.movies.Actor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class ActorServiceIT {

    @Autowired
    private ActorService actorService;

    public static Stream<Arguments> testUpdateAvailability() {
        return Stream.of(
                Arguments.of("Natalie Portman", false),
                Arguments.of("Samuel L. Jackson", true)
        );
    }

    @ParameterizedTest
    @MethodSource()
    void testUpdateAvailability(String artisticName, boolean availability) {
        this.actorService.updateAvailability(artisticName, availability);
        Actor actor = this.actorService.findByArtisticName(artisticName);
        assertEquals(availability, actor.isAvailable());
    }

    @Test
    void testUpdateActor() {
        Actor actor = this.actorService.findByArtisticName("Kate Winslet");
        actor.setRealName("Katerine Elizabeth Winslet");
        actor.setIsAvailable(false);
        this.actorService.updateActor(actor);

        Actor updatedActor = this.actorService.findByArtisticName("Kate Winslet");
        assertEquals("Katerine Elizabeth Winslet", updatedActor.getRealName());
        assertFalse(updatedActor.isAvailable());
    }

    @Test
    void testFindByArtisticName() {
        Actor actor = this.actorService.findByArtisticName("Scarlett Johansson");
        assertEquals("Scarlett Johansson", actor.getArtisticName());
        assertEquals("Scarlett Ingrid Johansson", actor.getRealName());
        assertTrue(actor.isAvailable());
        assertEquals(LocalDate.of(1984, 11, 22), actor.getBirthDate());
    }
}
