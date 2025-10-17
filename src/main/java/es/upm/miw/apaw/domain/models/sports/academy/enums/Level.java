package es.upm.miw.apaw.domain.models.sports.academy.enums;

import lombok.Getter;

@Getter
public enum Level {
    BEGINNER(0),
    INTERMEDIATE(1),
    ADVANCED(2);

    private final int value;

    Level(int value) {
        this.value = value;
    }
}
