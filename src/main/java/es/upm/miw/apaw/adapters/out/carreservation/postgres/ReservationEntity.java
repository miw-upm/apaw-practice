package es.upm.miw.apaw.adapters.out.carreservation.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.carreservation.Reservation;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReservationEntity {

    @Id
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    private Integer durationMinutes;

    private String destination;

    private Boolean businessTrip;

    private Integer passengerCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @Column(nullable = false)
    private UUID userId;

    public ReservationEntity(Reservation reservation) {
        BeanUtils.copyProperties(reservation, this, "car", "userSnapshot");

        if (reservation.getCar() != null) {
            this.car = new CarEntity(reservation.getCar());
        }

        if (reservation.getUserSnapshot() != null) {
            this.userId = reservation.getUserSnapshot().getId();
        }
    }

    public Reservation toDomain() {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(this, reservation, "car", "userId");

        if (this.car != null) {
            reservation.setCar(this.car.toDomain());
        }
        if (this.userId != null) {
            reservation.setUserSnapshot(UserSnapshot.builder().id(this.userId).build());
        }

        return reservation;
    }
}
