package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface HospitalRepository extends MongoRepository<HospitalEntity, String> {

}
