package es.upm.miw.apaw_practice.domain.models.movies;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ActorComposite implements ActorComponent {
    private final List<ActorComponent> components = new ArrayList<>();

    public void add(ActorComponent component) {
        components.add(component);
    }

    public void remove(ActorComponent component) {
        components.remove(component);
    }

    @Override
    public Stream<String> getArtisticNames() {
        return components.stream()
                .flatMap(ActorComponent::getArtisticNames);
    }

    @Override
    public Stream<String> getRealNames() {
        return components.stream()
                .flatMap(ActorComponent::getRealNames);
    }

    @Override
    public Stream<Boolean> getAvailabilityStatuses() {
        return components.stream()
                .flatMap(ActorComponent::getAvailabilityStatuses);
    }

    @Override
    public Stream<LocalDate> getBirthDates() {
        return components.stream()
                .flatMap(ActorComponent::getBirthDates);
    }
}
