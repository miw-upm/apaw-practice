package es.upm.miw.apaw.domain.model.euregulation;

import lombok.Getter;

@Getter
public enum LegalStatus {
    IN_FORCE("InForce"),
    REPEALED("Repealed"),
    UNDER_NEGOTIATION("UnderNegotiation");

    private final String value;

    LegalStatus(String value) {
        this.value = value;
    }
}
