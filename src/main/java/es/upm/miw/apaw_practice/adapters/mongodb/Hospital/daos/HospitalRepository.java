package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;

public interface HospitalRepository extends MongoRepository<HospitalEntity, String> {
    // Define your query methods here if needed
}
