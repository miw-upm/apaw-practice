package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.PatientEntity;

public interface PatientRepository extends MongoRepository<PatientEntity, String> {
    // Define your query methods here if needed
}
