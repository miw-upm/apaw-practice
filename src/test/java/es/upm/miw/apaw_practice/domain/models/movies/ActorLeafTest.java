package es.upm.miw.apaw_practice.domain.models.movies;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ActorLeafTest {
    @Test
    void testGetAttributes() {
        Actor actor = new Actor("ArtisticName1", "RealName1", true, LocalDate.of(1985, 5, 15));
        ActorLeaf actorLeaf = new ActorLeaf(actor);

        assertEquals("ArtisticName1", actorLeaf.getArtisticNames().collect(Collectors.joining()));
        assertEquals("RealName1", actorLeaf.getRealNames().collect(Collectors.joining()));
        assertTrue(actorLeaf.getAvailabilityStatuses().findFirst().orElse(false));
        assertEquals(LocalDate.of(1985, 5, 15), actorLeaf.getBirthDates().findFirst().orElse(null));
    }
}
