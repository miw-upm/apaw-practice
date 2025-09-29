package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {
    // Ejemplo de búsqueda:
    // List<DoctorEntity> findByUserId(String userId);
}