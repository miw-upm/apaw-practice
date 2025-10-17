package es.upm.miw.apaw.adapters.mongodb.winery.daos;

import es.upm.miw.apaw.adapters.mongodb.winery.entities.ReservationEntity;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.TastingSessionEntity;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.WineEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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

    @Autowired
    private WineRepository wineRepository;

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

    @Test
    void testFindReservationIdsByWineName_Exists() {
        WineEntity wineMasseto = WineEntity.builder().id(UUID.randomUUID()).name("Masseto").build();
        wineRepository.save(wineMasseto);
        TastingSessionEntity session = TastingSessionEntity.builder()
                .id(UUID.randomUUID())
                .date(LocalDate.now())
                .capacity(10)
                .location("Madrid")
                .wineEntities(List.of(wineMasseto))
                .evaluationEntities(List.of())
                .build();
        tastingSessionRepository.save(session);

        ReservationEntity reservation = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .bookingDate(LocalDateTime.now())
                .totalCost(new BigDecimal("100"))
                .confirmed(true)
                .tastingSessionEntity(session)
                .build();
        reservationRepository.save(reservation);

        List<UUID> result = reservationRepository.findAll().stream()
                .filter(res -> res.getTastingSessionEntity() != null &&
                        res.getTastingSessionEntity().getWineEntities() != null &&
                        res.getTastingSessionEntity().getWineEntities().stream()
                                .filter(Objects::nonNull)
                                .anyMatch(w -> w.getName() != null && w.getName().equalsIgnoreCase("Masseto")))
                .map(ReservationEntity::getId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        assertThat(result).containsExactly(reservation.getId());
    }

    @Test
    void testFindReservationIdsByWineName_NotExists() {
        TastingSessionEntity session = TastingSessionEntity.builder()
                .id(UUID.randomUUID())
                .date(LocalDate.now())
                .capacity(10)
                .location("Madrid")
                .wineEntities(List.of())
                .evaluationEntities(List.of())
                .build();
        tastingSessionRepository.save(session);

        ReservationEntity reservation = ReservationEntity.builder()
                .id(UUID.randomUUID())
                .userId(UUID.randomUUID())
                .bookingDate(LocalDateTime.now())
                .totalCost(new BigDecimal("100"))
                .confirmed(true)
                .tastingSessionEntity(session)
                .build();
        reservationRepository.save(reservation);

        List<UUID> result = reservationRepository.findAll().stream()
                .filter(res -> res.getTastingSessionEntity() != null &&
                        res.getTastingSessionEntity().getWineEntities() != null &&
                        res.getTastingSessionEntity().getWineEntities().stream()
                                .filter(Objects::nonNull)
                                .anyMatch(w -> w.getName() != null && w.getName().equalsIgnoreCase("Syrah")))
                .map(ReservationEntity::getId)
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        assertThat(result).isEmpty();
    }
}
