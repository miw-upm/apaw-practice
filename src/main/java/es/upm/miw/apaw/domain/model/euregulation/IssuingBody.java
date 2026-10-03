package es.upm.miw.apaw.domain.model.euregulation;

import lombok.Getter;

@Getter
public enum IssuingBody {
    EUROPEAN_COMMISSION("EuropeanCommission"),
    EUROPEAN_PARLIAMENT("EuropeanParliament"),
    COUNCIL_OF_THE_EU("CouncilOfTheEU"),
    ECB("ECB");

    private final String value;

    IssuingBody(String value) {
        this.value = value;
    }
}
