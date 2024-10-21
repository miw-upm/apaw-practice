package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;
import java.util.stream.Stream;

public class ActorLeaf implements ActorComponent {
    private final Actor actor;

    public ActorLeaf(Actor actor) {
        this.actor = actor;
    }

    @Override
    public Stream<String> getArtisticNames() {
        return Stream.of(actor.getArtisticName());
    }

    @Override
    public Stream<String> getRealNames() {
        return Stream.of(actor.getRealName());
    }

    @Override
    public Stream<Boolean> getAvailabilityStatuses() {
        return Stream.of(actor.isAvailable());
    }

    @Override
    public Stream<LocalDate> getBirthDates() {
        return Stream.of(actor.getBirthDate());
    }
}
