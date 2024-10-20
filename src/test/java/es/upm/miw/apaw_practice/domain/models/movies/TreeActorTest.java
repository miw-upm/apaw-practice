package es.upm.miw.apaw_practice.domain.models.movies;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeActorTest {
    @Test
    void testCompositeWithLeaves() {
        Actor actor1 = new Actor("ArtisticName1", "RealName1", true, LocalDate.of(1985, 5, 15));
        Actor actor2 = new Actor("ArtisticName2", "RealName2", false, LocalDate.of(1990, 10, 30));

        ActorLeaf actorLeaf1 = new ActorLeaf(actor1);
        ActorLeaf actorLeaf2 = new ActorLeaf(actor2);

        ActorComposite actorComposite = new ActorComposite();
        actorComposite.add(actorLeaf1);
        actorComposite.add(actorLeaf2);

        assertEquals("ArtisticName1,ArtisticName2",
                actorComposite.getArtisticNames().collect(Collectors.joining(",")));
        assertEquals("RealName1,RealName2",
                actorComposite.getRealNames().collect(Collectors.joining(",")));
    }

    @Test
    void testNestedComposite() {
        Actor actor1 = new Actor("ArtisticName1", "RealName1", true, LocalDate.of(1985, 5, 15));
        Actor actor2 = new Actor("ArtisticName2", "RealName2", false, LocalDate.of(1990, 10, 30));
        Actor actor3 = new Actor("ArtisticName3", "RealName3", true, LocalDate.of(2000, 1, 10));

        ActorLeaf actorLeaf1 = new ActorLeaf(actor1);
        ActorLeaf actorLeaf2 = new ActorLeaf(actor2);
        ActorLeaf actorLeaf3 = new ActorLeaf(actor3);

        ActorComposite actorComposite1 = new ActorComposite();
        actorComposite1.add(actorLeaf1);
        actorComposite1.add(actorLeaf2);

        ActorComposite actorComposite2 = new ActorComposite();
        actorComposite2.add(actorLeaf3);
        actorComposite2.add(actorComposite1);

        assertEquals("ArtisticName3,ArtisticName1,ArtisticName2",
                actorComposite2.getArtisticNames().collect(Collectors.joining(",")));
    }
}
