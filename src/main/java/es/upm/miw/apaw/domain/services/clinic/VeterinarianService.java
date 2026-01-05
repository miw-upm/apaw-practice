package es.upm.miw.apaw.domain.services.clinic;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import es.upm.miw.apaw.domain.persistenceports.clinic.VeterinarianPersistence;
import org.springframework.stereotype.Service;

@Service
public class VeterinarianService {

    private final VeterinarianPersistence veterinarianPersistence;

    public VeterinarianService(VeterinarianPersistence veterinarianPersistence) {
        this.veterinarianPersistence = veterinarianPersistence;
    }

    public Veterinarian readByLicense(Long licenseNumber) {
        return this.veterinarianPersistence.findByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new NotFoundException("Veterinarian not found: " + licenseNumber));
    }
}