package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;
import java.util.stream.Stream;

interface ActorComponent {
    public abstract Stream<String> getArtisticNames();
    public abstract Stream<String> getRealNames();
    public abstract Stream<Boolean> getAvailabilityStatuses();
    public abstract Stream<LocalDate> getBirthDates();
}
