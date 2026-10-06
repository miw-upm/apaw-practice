package es.upm.miw.apaw.domain.model.powerofattorney;

import java.util.UUID;

public record CreationPowerOfAttorneyParty (
        Integer age,
        Boolean fullMentalCapacity,
        String companyName,
        Boolean representationCompany,
        UUID userId){
}
