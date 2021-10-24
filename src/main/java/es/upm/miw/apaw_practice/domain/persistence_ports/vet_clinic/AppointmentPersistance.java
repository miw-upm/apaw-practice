package es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic;

import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Stream;

@Repository
public interface AppointmentPersistance {
    Appointment create(Appointment appointment);

    Appointment update(LocalDate date, LocalTime hour, Appointment appointment);

    Appointment read(LocalDate date, LocalTime hour);

    Stream<Appointment> findByConsumed(Boolean consumed);
}
