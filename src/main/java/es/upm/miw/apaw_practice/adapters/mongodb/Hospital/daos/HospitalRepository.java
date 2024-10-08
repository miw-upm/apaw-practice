
package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.HospitalEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HospitalRepository extends MongoRepository<HospitalEntity, String> {
}
