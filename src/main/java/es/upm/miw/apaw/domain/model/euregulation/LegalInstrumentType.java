package es.upm.miw.apaw.domain.model.euregulation;

import lombok.Getter;

@Getter
public enum LegalInstrumentType {
    REGULATION("Regulation"),
    DIRECTIVE("Directive"),
    DECISION("Decision"),
    RECOMMENDATION("Recommendation");

    private final String value;

    LegalInstrumentType(String value) {
        this.value = value;
    }
}
