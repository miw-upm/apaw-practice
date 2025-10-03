package es.upm.miw.apaw.domain.models.sports.academy.enums;

import lombok.Getter;

@Getter
public enum TargetAudience {
    KIDS(1),
    TWEENS(2),
    TEENAGERS(3);

    private final int value;

    TargetAudience(int value) {
        this.value = value;
    }

}
