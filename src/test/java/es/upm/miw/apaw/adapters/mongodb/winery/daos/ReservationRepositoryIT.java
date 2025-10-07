package es.upm.miw.apaw.adapters.mongodb.winery.daos;

import es.upm.miw.apaw.adapters.mongodb.winery.entities.ReservationEntity;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.TastingSessionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class ReservationRepositoryIT {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private TastingSessionRepository tastingSessionRepository;

    @Test
    void testCreate() {
        TastingSessionEntity tastingSessionEntity = TastingSessionEntity.builder()
                .id(UUID.randomUUID())
                .date(LocalDate.now())
                .capacity(15)
                .location("Madrid Cellar")
                .wineEntities(List.of())
                .evaluationEntities(List.of())
                .build();
        tastingSessionRepository.save(tastingSessionEntity);

        UUID userId = UUID.randomUUID();

        ReservationEntity reservationEntity = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .bookingDate(LocalDateTime.now())
                .totalCost(new BigDecimal("50.00"))
                .confirmed(true)
                .userId(userId)
                .tastingSessionEntity(tastingSessionEntity)
                .build();

        reservationRepository.save(reservationEntity);

        assertTrue(this.reservationRepository.findById(reservationEntity.getId()).isPresent());
        ReservationEntity reservation = reservationRepository.findById(reservationEntity.getId()).get();
        assertThat(reservation.getUserId()).isEqualTo(userId);
        assertThat(reservation.getTastingSessionEntity().getId()).isEqualTo(tastingSessionEntity.getId());
    }
}
