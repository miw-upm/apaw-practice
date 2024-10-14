package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.daos.AppointmentRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.Hospital.entities.AppointmentEntity;
import es.upm.miw.apaw_practice.domain.models.Hospital.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.AppointmentPersistence;

import java.util.List;
import java.util.stream.Collectors;

@Repository("appointmentPersistence")
public class AppointmentPersistenceMongodb implements AppointmentPersistence {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentPersistenceMongodb(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> findAll() {
        return this.appointmentRepository.findAll().stream()
                .map(AppointmentEntity::toAppointment)
                .collect(Collectors.toList());
    }
}
