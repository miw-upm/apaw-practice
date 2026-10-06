package es.upm.miw.apaw.domain.model.judicialcourt;

public record JudicialCourtTypeUpdate(
        String name,
        String description,
        String code,
        String jurisdiction,
        Boolean active
) {
}
