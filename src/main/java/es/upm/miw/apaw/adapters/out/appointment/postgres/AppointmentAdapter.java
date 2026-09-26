package es.upm.miw.apaw.adapters.out.appointment.postgres;

import es.upm.miw.apaw.domain.model.appointment.Appointment;
import es.upm.miw.apaw.domain.ports.out.appointment.AppointmentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppointmentAdapter implements AppointmentGateway {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentLocationRepository appointmentLocationRepository;

    @Override
    public Appointment create(Appointment appointment) {
        AppointmentEntity entity = new AppointmentEntity(appointment);
        if (appointment.getLocation() != null) {
            entity.setLocation(this.appointmentLocationRepository
                    .getReferenceById(appointment.getLocation().getId()));
        }
        this.appointmentRepository.save(entity);
        return appointment;
    }
}
