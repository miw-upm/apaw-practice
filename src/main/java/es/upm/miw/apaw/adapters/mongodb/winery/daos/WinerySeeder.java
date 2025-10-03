package es.upm.miw.apaw.adapters.mongodb.winery.daos;

import es.upm.miw.apaw.adapters.mongodb.winery.entities.EvaluationEntity;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.ReservationEntity;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.TastingSessionEntity;
import es.upm.miw.apaw.adapters.mongodb.winery.entities.WineEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class WinerySeeder {

    private final WineRepository wineRepository;
    private final TastingSessionRepository tastingSessionRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public WinerySeeder(WineRepository wineRepository,
                        TastingSessionRepository tastingSessionRepository,
                        ReservationRepository reservationRepository) {
        this.wineRepository = wineRepository;
        this.tastingSessionRepository = tastingSessionRepository;
        this.reservationRepository = reservationRepository;
    }

    public void seedDatabase() {
        log.warn("------- Winery Initial Load -----------");

        // Wines
        WineEntity[] wines = {
                WineEntity.builder().idWine(1L).name("Cabernet Sauvignon").year(2020)
                        .alcoholPercentage(13.5).price(new BigDecimal("25.50")).build(),
                WineEntity.builder().idWine(2L).name("Merlot").year(2019)
                        .alcoholPercentage(14.0).price(new BigDecimal("18.90")).build(),
                WineEntity.builder().idWine(3L).name("Chardonnay").year(2021)
                        .alcoholPercentage(12.5).price(new BigDecimal("15.75")).build()
        };
        this.wineRepository.saveAll(Arrays.asList(wines));

        // Evaluations (creadas directamente en TastingSessions)
        EvaluationEntity eval1 = EvaluationEntity.builder()
                .idEvaluation(1L).score(5).comment("Excellent balance").build();
        EvaluationEntity eval2 = EvaluationEntity.builder()
                .idEvaluation(2L).score(4).comment("Fruity and smooth").build();

        // TastingSessions
        TastingSessionEntity[] sessions = {
                TastingSessionEntity.builder().idSession(100L).date(LocalDate.now().plusDays(5))
                        .capacity(20).location("Main Hall")
                        .wineEntities(List.of(wines[0], wines[1]))
                        .evaluationEntities(List.of(eval1))
                        .build(),
                TastingSessionEntity.builder().idSession(101L).date(LocalDate.now().plusDays(10))
                        .capacity(15).location("Garden Room")
                        .wineEntities(List.of(wines[2]))
                        .evaluationEntities(List.of(eval2))
                        .build()
        };
        this.tastingSessionRepository.saveAll(Arrays.asList(sessions));

        // Reservations
        ReservationEntity[] reservations = {
                ReservationEntity.builder().idReservation(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100"))
                        .reservationDate(LocalDateTime.now().plusDays(1))
                        .totalCost(new BigDecimal("40.00"))
                        .confirmed(true)
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                        .tastingSessionId(sessions[0].getIdSession())
                        .build(),
                ReservationEntity.builder().idReservation(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101"))
                        .reservationDate(LocalDateTime.now().plusDays(2))
                        .totalCost(new BigDecimal("20.00"))
                        .confirmed(false)
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1001"))
                        .tastingSessionId(sessions[1].getIdSession())
                        .build()
        };
        this.reservationRepository.saveAll(Arrays.asList(reservations));

        log.warn("        ------- winery");
    }

    public void deleteAll() {
        this.reservationRepository.deleteAll();
        this.tastingSessionRepository.deleteAll();
        this.wineRepository.deleteAll();
    }
}

