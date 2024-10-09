package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.PatientEntities;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface PatientRepository extends MongoRepository<PatientEntities, String> {

}
