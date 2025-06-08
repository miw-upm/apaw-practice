package es.upm.miw.apaw_practice.domain.models.music_festival;

import java.time.LocalDateTime;

public interface StageBuilders {
    interface Name<T> {
        Location<T> name(String name);
    }

    interface Location<T> {
        Capacity<T> location(String location);
    }

    interface Capacity<T> {
        Optionals<T> capacity(int capacity);
    }

    interface Optionals<T> {
        Optionals<T> openTime(LocalDateTime openTime);

        T build();
    }
}