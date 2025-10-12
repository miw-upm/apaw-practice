package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {
    // Método necesario para la lógica de negocio y CRUD por clave de negocio
    Optional<DoctorEntity> findByLicenseNumber(Long licenseNumber);
}