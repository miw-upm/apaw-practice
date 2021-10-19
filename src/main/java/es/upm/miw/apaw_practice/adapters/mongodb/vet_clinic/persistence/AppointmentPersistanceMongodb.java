package es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.daos.AppointmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.vet_clinic.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.vet_clinic.Appointment;
import es.upm.miw.apaw_practice.domain.persistence_ports.vet_clinic.AppointmentPersistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository("appointmentPersistance")
public class AppointmentPersistanceMongodb implements AppointmentPersistance {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentPersistanceMongodb(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment create(Appointment appointment) {
        return this.appointmentRepository
                .save(new AppointmentEntity(appointment))
                .toAppointment();
    }

    @Override
    public Appointment read(LocalDate date, LocalTime hour) {
        return this.appointmentRepository
                .findAppointmentByDateAndHour(date, hour)
                .orElseThrow(() -> new NotFoundException("Appointment date: " + date +
                        ", and hour: " + hour))
                .toAppointment();
    }

    @Override
    public Appointment update(LocalDate date, LocalTime hour, Appointment appointment) {
        AppointmentEntity appointmentEntity = this.appointmentRepository
                .findAppointmentByDateAndHour(appointment.getDate(), appointment.getHour())
                .orElseThrow(() -> new NotFoundException("Appointment date: " + appointment.getDate() +
                        ", and hour: " + appointment.getHour()));
        appointmentEntity.fromAppointment(appointment);
        return this.appointmentRepository
                .save(appointmentEntity)
                .toAppointment();
    }
}
