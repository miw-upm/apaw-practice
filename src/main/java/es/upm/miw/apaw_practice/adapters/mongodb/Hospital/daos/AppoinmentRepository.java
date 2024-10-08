package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<AppointmentEntity, Integer> {
    // métodos personalizados si es necesario
}