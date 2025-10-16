package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {

    // Método para buscar por la clave de negocio (licenseNumber)
    Optional<DoctorEntity> findByLicenseNumber(Long licenseNumber);
}