package es.upm.miw.apaw.domain.models.sports.academy.enums;

import lombok.Getter;

@Getter
public enum Level {
    BEGINNER(1),
    INTERMEDIATE(2),
    ADVANCED(3);

    private final int value;

    Level(int value) {
        this.value = value;
    }
}
