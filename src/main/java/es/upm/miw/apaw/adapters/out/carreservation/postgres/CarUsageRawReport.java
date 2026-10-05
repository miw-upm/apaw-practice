package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarUsageRawReport {
    private String carRegistration;
    private UUID userId;
    private Long totalReservations;
    private Long totalDurationMinutes;
}