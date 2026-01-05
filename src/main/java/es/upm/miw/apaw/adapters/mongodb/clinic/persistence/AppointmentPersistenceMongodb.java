package es.upm.miw.apaw.adapters.mongodb.clinic.persistence;

import es.upm.miw.apaw.adapters.mongodb.clinic.daos.AppointmentRepository;
import es.upm.miw.apaw.adapters.mongodb.clinic.entities.AppointmentEntity;
import es.upm.miw.apaw.domain.models.clinic.Appointment;
import es.upm.miw.apaw.domain.persistenceports.clinic.AppointmentPersistence;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("appointmentPersistence")
public class AppointmentPersistenceMongodb implements AppointmentPersistence {

    private final AppointmentRepository appointmentRepository;

    public AppointmentPersistenceMongodb(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Optional<Appointment> findById(UUID id) {
        return this.appointmentRepository.findById(id)
                .map(AppointmentEntity::toAppointment);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepository.save(new AppointmentEntity(appointment)).toAppointment();
    }
}