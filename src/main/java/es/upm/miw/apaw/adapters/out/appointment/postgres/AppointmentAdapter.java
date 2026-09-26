package es.upm.miw.apaw.adapters.out.appointment.postgres;

import es.upm.miw.apaw.domain.ports.out.appointment.AppointmentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppointmentAdapter implements AppointmentGateway {

    private final AppointmentRepository appointmentRepository;
}
