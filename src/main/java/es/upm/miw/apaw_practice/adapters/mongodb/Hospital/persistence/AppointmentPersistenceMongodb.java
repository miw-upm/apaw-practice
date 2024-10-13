package es.upm.miw.apaw_practice.adapters.mongodb.Hospital.persistence;

import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hospital.Appointment;
import es.upm.miw.apaw_practice.domain.persistence_ports.Hospital.AppoinmentPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository("appoinmentPersistence")
public class AppointmentPersistenceMongodb implements AppoinmentPersistence {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment create(Appointment appointment) {
        AppointmentEntity appointmentEntity = new AppointmentEntity(appointment);
        return appointmentRepository.save(appointmentEntity).toModel();
    }

    @Override
    public Optional<Appointment> read(String id) {
        return appointmentRepository.findById(id).map(AppointmentEntity::toModel);
    }

    @Override
    public Appointment update(Appointment appointment) {
        // Check if the appointment exists before updating
        if (!appointmentRepository.existsById(appointment.getId())) {
            throw new NotFoundException("Appointment not found with id: " + appointment.getId());
        }
        AppointmentEntity appointmentEntity = new AppointmentEntity(appointment);
        return appointmentRepository.save(appointmentEntity).toModel();
    }

    @Override
    public void delete(String id) {
        // Check if the appointment exists before deleting
        if (!appointmentRepository.existsById(id)) {
            throw new NotFoundException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<Appointment> readAll() {
        return appointmentRepository.findAll().stream()
                .map(AppointmentEntity::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppointmentEntity> findById(String id) {
        return appointmentRepository.findById(id);
    }
}
