package es.upm.miw.apaw.adapters.mongodb.clinic.daos;

import es.upm.miw.apaw.adapters.mongodb.clinic.entities.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface AppointmentRepository extends MongoRepository<AppointmentEntity, UUID> {

}
