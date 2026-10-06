package es.upm.miw.apaw.domain.model.judicialcourt;

public record JudicialCourtTypePatch(
        String name,
        String description,
        String code,
        String jurisdiction,
        Boolean active
) {
}
