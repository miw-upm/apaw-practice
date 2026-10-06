package es.upm.miw.apaw.domain.model.powerofattorney;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PowerOfAttorneyPartyPatch (
        @NotNull UUID id,
        Integer age,
        Boolean fullMentalCapacity){
}
