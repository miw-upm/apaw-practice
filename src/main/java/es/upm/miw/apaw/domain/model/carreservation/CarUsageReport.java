package es.upm.miw.apaw.domain.model.carreservation;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarUsageReport {
    private String carRegistration;
    private UserSnapshot userSnapshot;
    private Long totalReservations;
    private Long totalDurationMinutes;
}