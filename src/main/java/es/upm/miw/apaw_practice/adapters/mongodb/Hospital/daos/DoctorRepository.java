package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;

public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {
    // Define your query methods here if needed
}
