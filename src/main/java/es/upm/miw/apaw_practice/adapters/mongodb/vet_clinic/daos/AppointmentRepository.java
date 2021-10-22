package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.Stream;

public interface AppointmentRepository extends MongoRepository<AppointmentEntity, String> {
    Optional<AppointmentEntity> findAppointmentByDateAndHour(LocalDate date, LocalTime hour);
    Optional<AppointmentEntity> findAppointmentByDate(LocalDate date);
}
