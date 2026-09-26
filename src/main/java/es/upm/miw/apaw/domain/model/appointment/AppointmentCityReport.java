package es.upm.miw.apaw.domain.model.appointment;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentCityReport {

    private UUID clientId;
    private UserSnapshot client;
    private String city;
    private long totalAppointments;

    public AppointmentCityReport(UUID clientId, String city, long totalAppointments) {
        this.clientId = clientId;
        this.city = city;
        this.totalAppointments = totalAppointments;
    }
}
