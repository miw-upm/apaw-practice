package es.upm.miw.apaw.domain.model.euregulation;

import lombok.Getter;

@Getter
public enum ApplicationArea {
    DATA_PROTECTION("DataProtection"),
    FINANCIAL("Financial"),
    DIGITAL_TECHNOLOGY("DigitalTechnology"),
    ENVIRONMENTAL("Environmental"),
    LABOR("Labor"),
    COMPETITION("Competition"),
    CONSUMER_PROTECTION("ConsumerProtection");

    private final String value;

    ApplicationArea(String value) {
        this.value = value;
    }
}
