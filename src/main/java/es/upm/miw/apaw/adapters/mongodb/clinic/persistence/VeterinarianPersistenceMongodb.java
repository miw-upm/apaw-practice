package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.VeterinarianRepository;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.VeterinarianEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.clinic.Veterinarian;
import es.upm.miw.apaw.domain.persistenceports.clinic.VeterinarianPersistence;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public void delete(Veterinarian veterinarian) {
        if (!this.veterinarianRepository.existsByLicenseNumber(veterinarian.getLicenseNumber())) {
            throw new NotFoundException("Veterinarian not found: " + veterinarian.getLicenseNumber());
        }
        this.veterinarianRepository.deleteByLicenseNumber(veterinarian.getLicenseNumber());
    }

    @Override
    public void addAppointments(Long licenseNumber, UUID appointmentId) {
        VeterinarianEntity veterinarianEntity = this.veterinarianRepository.findByLicenseNumber(licenseNumber).orElseThrow(
                () -> new NotFoundException("Veterinarian not found: " + licenseNumber)
        );
        if(veterinarianEntity.getAppointments() == null){
            veterinarianEntity.setAppointments(new ArrayList<>());
        }
        veterinarianEntity.getAppointments().add(appointmentId);
        this.veterinarianRepository.save(veterinarianEntity);
    }

    @Override
    public List<Veterinarian> findByAppointmentIds(List<UUID> appointmentId) {
        return this.veterinarianRepository.findByAppointmentIds(appointmentId).stream()
                .map(VeterinarianEntity::toVeterinarian).toList();
    }
}