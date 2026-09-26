package es.upm.miw.apaw.domain.ports.out.appointment;

import es.upm.miw.apaw.domain.model.appointment.Appointment;
import es.upm.miw.apaw.domain.model.appointment.AppointmentCityReport;

import java.util.List;

public interface AppointmentGateway {
    Appointment create(Appointment appointment);
    List<AppointmentCityReport> findCityReport();
}
