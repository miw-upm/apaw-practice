package es.upm.miw.apaw.domain.models.sports.academy.enums;

import lombok.Getter;

@Getter
public enum RelationShip {
    FATHER(1),
    MOTHER(2),
    SIBLING(3),
    UNCLE(4),
    AUNT(5),
    GRAND_FATHER(6),
    GRAND_MOTHER(7),
    OTHER(8);

    private final int value;

    RelationShip(int value) {
        this.value = value;
    }
}
