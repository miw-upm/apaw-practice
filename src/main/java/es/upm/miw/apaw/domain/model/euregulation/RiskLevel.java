package es.upm.miw.apaw.domain.model.euregulation;

import lombok.Getter;

@Getter
public enum RiskLevel {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String value;

    RiskLevel(String value) {
        this.value = value;
    }
}
