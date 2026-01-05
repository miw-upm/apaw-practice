package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.VeterinarianRepository;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.VeterinarianEntity;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import es.upm.miw.apaw.domain.persistenceports.clinic.VeterinarianPersistence;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("veterinarianPersistence")
public class VeterinarianPersistenceMongodb implements VeterinarianPersistence {

    private final VeterinarianRepository veterinarianRepository;

    public VeterinarianPersistenceMongodb(VeterinarianRepository veterinarianRepository) {
        this.veterinarianRepository = veterinarianRepository;
    }

    @Override
    public Optional<Veterinarian> findByLicenseNumber(Long licenseNumber) {
        return this.veterinarianRepository.findByLicenseNumber(licenseNumber)
                .map(VeterinarianEntity::toVeterinarian);
    }
}