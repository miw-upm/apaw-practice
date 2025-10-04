package es.upm.miw.apaw.domain.models.sports.academy.enums;

import lombok.Getter;

@Getter
public enum RelationShip {
    FATHER(0),
    MOTHER(1),
    SIBLING(2),
    UNCLE(3),
    AUNT(4),
    GRAND_FATHER(5),
    GRAND_MOTHER(6),
    OTHER(7);

    private final int value;

    RelationShip(int value) {
        this.value = value;
    }
}
