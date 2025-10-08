package es.upm.miw.apaw.domain.models.sports.academy.enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE(0),
    FEMALE(1),
    OTHER(2);

    private final int value;

    Gender(int value) {
        this.value = value;
    }
}
