package es.upm.miw.apaw.domain.model.carreservation;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreationReservation {

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    private String destination;

    private Boolean businessTrip;

    private Integer passengerCount;

    @NotNull
    private UUID userId;

    @NotNull
    private UUID carId;

}