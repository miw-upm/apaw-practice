package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.PatientEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<PatientEntity, String> {
}
