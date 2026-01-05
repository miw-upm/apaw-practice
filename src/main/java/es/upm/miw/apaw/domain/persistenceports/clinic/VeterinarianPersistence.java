package es.upm.miw.apaw.domain.persistenceports.clinic;

import es.upm.miw.apaw.domain.models.clinic.Veterinarian;

import java.util.Optional;

public interface VeterinarianPersistence {
    Optional<Veterinarian> findByLicenseNumber(Long licenseNumber);
}