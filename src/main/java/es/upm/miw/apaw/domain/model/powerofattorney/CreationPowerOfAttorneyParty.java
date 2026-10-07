package es.upm.miw.apaw.domain.model.powerofattorney;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreationPowerOfAttorneyParty (
        @NotNull Integer age,
        @NotNull Boolean fullMentalCapacity,
        String companyName,
        @NotNull Boolean representationCompany,
        @NotNull UUID userId){
}
