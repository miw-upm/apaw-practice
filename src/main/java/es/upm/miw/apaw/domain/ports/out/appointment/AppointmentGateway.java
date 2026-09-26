package es.upm.miw.apaw.domain.ports.out.appointment;

import es.upm.miw.apaw.domain.model.appointment.Appointment;

public interface AppointmentGateway {
    Appointment create(Appointment appointment);
}
