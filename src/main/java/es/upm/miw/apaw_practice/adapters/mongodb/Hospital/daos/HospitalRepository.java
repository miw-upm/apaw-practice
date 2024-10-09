package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntities;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface HospitalRepository extends MongoRepository<HospitalEntities, String> {

}
