package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
public interface AppointmentRepository extends MongoRepository<AppointmentEntity, String> {
    // define your query methods here
}
