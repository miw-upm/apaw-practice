package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {

}
