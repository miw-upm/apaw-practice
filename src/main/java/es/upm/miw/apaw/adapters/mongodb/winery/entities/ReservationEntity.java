package es.upm.miw.apaw.adapters.mongodb.winery.entities;

import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.winery.Reservation;
import es.upm.miw.apaw.domain.models.winery.TastingSession;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document
public class ReservationEntity {
    @EqualsAndHashCode.Include
    @Id
    private UUID idReservation;
    private LocalDateTime reservationDate;
    private BigDecimal totalCost;
    private Boolean confirmed;
    private UUID userId;
    private Long tastingSessionId;

    public ReservationEntity(Reservation reservation) {
        BeanUtils.copyProperties(reservation, this, "user", "tastingSession");
        this.userId = reservation.getUser().getId();
        this.tastingSessionId = reservation.getTastingSession().getIdSession();
    }

    public Reservation toReservation() {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(this, reservation, "user", "tastingSession");
        reservation.setUser(UserDto.builder().id(userId).build());
        reservation.setTastingSession(TastingSession.builder().idSession(tastingSessionId).build());
        return reservation;
    }
}
