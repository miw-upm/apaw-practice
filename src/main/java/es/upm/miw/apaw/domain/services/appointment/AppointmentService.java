package es.upm.miw.apaw.domain.services.appointment;

import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.appointment.Appointment;
import es.upm.miw.apaw.domain.model.appointment.AppointmentCityReport;
import es.upm.miw.apaw.domain.model.appointment.CreationAppointment;
import es.upm.miw.apaw.domain.ports.out.appointment.AppointmentGateway;
import es.upm.miw.apaw.domain.ports.out.appointment.AppointmentLocationGateway;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentGateway appointmentGateway;
    private final AppointmentLocationGateway appointmentLocationGateway;
    private final UserFinder userFinder;

    public Appointment create(CreationAppointment creation) {
        Appointment appointment = new Appointment();
        BeanUtils.copyProperties(creation, appointment, "userId", "locationId");
        appointment.setClient(this.userFinder.read(creation.getUserId()));
        if (creation.getLocationId() != null) {
            appointment.setLocation(this.appointmentLocationGateway.read(creation.getLocationId())
                    .orElseThrow(() -> new NotFoundException(
                            "Appointment location id not found: " + creation.getLocationId())));
        }
        appointment.doDefault();
        return this.appointmentGateway.create(appointment);
    }

    public List<AppointmentCityReport> findCityReport() {
        List<AppointmentCityReport> report = this.appointmentGateway.findCityReport();
        if (report.isEmpty()) {
            return List.of();
        }
        Set<UUID> clientIds = report.stream()
                .map(AppointmentCityReport::getClientId)
                .collect(Collectors.toSet());
        Map<UUID, UserSnapshot> usersById = this.userFinder.findByIds(clientIds).stream()
                .collect(Collectors.toMap(UserSnapshot::getId, Function.identity()));
        report.forEach(r -> r.setClient(usersById.get(r.getClientId())));
        return report;
    }
}
