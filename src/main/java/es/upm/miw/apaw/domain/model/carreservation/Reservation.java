package es.upm.miw.apaw.domain.model.carreservation;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reservation {

    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private LocalTime endTime;

    private Integer durationMinutes;

    private String destination;

    private Boolean businessTrip;

    private Integer passengerCount;

    private UserSnapshot userSnapshot;

    private Car car;

    public void doDefault() {
        this.id = UUID.randomUUID();
        if (this.businessTrip == null) {
            this.businessTrip = true;
        }
        if (this.passengerCount == null) {
            this.passengerCount = 1;
        }
        if (this.durationMinutes == null && this.startTime != null && this.endTime != null) {
            this.durationMinutes =
                    (int) java.time.Duration.between(this.startTime, this.endTime).toMinutes();
        }
    }

    public Reservation ofSummary() {
        return Reservation.builder()
                .id(this.id)
                .date(this.date)
                .startTime(this.startTime)
                .endTime(this.endTime)
                .durationMinutes(this.durationMinutes)
                .destination(this.destination)
                .businessTrip(this.businessTrip)
                .passengerCount(this.passengerCount)
                .userSnapshot(UserSnapshot.builder()
                        .id(this.userSnapshot.getId())
                        .mobile(this.userSnapshot.getMobile())
                        .firstName(this.userSnapshot.getFirstName())
                        .build())
                .build();
    }
}
