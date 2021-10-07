package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface AppointmentRepository extends MongoRepository<AppointmentEntity, String> {
    Optional<AppointmentEntity> findAppointmentByDate(LocalDate date);
}
