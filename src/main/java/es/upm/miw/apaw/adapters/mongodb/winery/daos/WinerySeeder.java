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
                WineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .name("Cabernet Sauvignon").year(2020)
                        .alcoholPercentage(13.5).price(new BigDecimal("25.50")).build(),
                WineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .name("Merlot").year(2019)
                        .alcoholPercentage(14.0).price(new BigDecimal("18.90")).build(),
                WineEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .name("Chardonnay").year(2021)
                        .alcoholPercentage(12.5).price(new BigDecimal("15.75")).build()
        };
        this.wineRepository.saveAll(Arrays.asList(wines));

        // Evaluations (created in TastingSessions)
        EvaluationEntity eval1 = EvaluationEntity.builder()
                .score(5).comment("Great organization and excellent wine selection")
                .recommended(true).build();

        EvaluationEntity eval2 = EvaluationEntity.builder()
                .score(2).comment("Poor organization, the session started late and felt rushed")
                .recommended(false).build();

        // TastingSessions
        TastingSessionEntity[] sessions = {
                TastingSessionEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0100")).date(LocalDate.now().plusDays(5))
                        .capacity(20).location("Main Hall")
                        .wineEntities(List.of(wines[0], wines[1]))
                        .evaluationEntities(List.of(eval1))
                        .build(),
                TastingSessionEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0101")).date(LocalDate.now().plusDays(10))
                        .capacity(15).location("Garden Room")
                        .wineEntities(List.of(wines[2]))
                        .evaluationEntities(List.of(eval2))
                        .build()
        };
        this.tastingSessionRepository.saveAll(Arrays.asList(sessions));

        // Reservations
        ReservationEntity[] reservations = {
                ReservationEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                        .bookingDate(LocalDateTime.now().plusDays(1))
                        .totalCost(new BigDecimal("40.00"))
                        .confirmed(true)
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                        .tastingSessionEntity(sessions[0])
                        .build(),
                ReservationEntity.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1001"))
                        .bookingDate(LocalDateTime.now().plusDays(2))
                        .totalCost(new BigDecimal("20.00"))
                        .confirmed(false)
                        .userId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1001"))
                        .tastingSessionEntity(sessions[1])
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

