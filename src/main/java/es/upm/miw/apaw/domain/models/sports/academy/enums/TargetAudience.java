package es.upm.miw.apaw.domain.models.sports.academy.enums;

import lombok.Getter;

@Getter
public enum TargetAudience {
    KIDS(0),
    TWEENS(1),
    TEENAGERS(2);

    private final int value;

    TargetAudience(int value) {
        this.value = value;
    }
}