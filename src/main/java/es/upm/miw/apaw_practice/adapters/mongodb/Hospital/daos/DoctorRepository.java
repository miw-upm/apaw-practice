package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {
    // Método para verificar si existe un doctor por su DNI
    boolean existsByDni(String dni)
}
