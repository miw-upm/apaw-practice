package es.upm.miw.apaw.adapters.mongodb.metro.daos;

import es.upm.miw.apaw.adapters.mongodb.metro.entities.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;
import java.util.List;
import java.math.BigDecimal;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class MetroSeeder {

    private final TrainStationRepository trainStationRepository;
    private final TrainLineRepository trainLineRepository;
    private final TrainRepository trainRepository;
    private final ZoneRepository zoneRepository;


    @Autowired
    public MetroSeeder(TrainStationRepository trainStationRepository, TrainLineRepository trainLineRepository,
                       TrainRepository trainRepository, ZoneRepository zoneRepository) {
        this.trainStationRepository = trainStationRepository;
        this.trainLineRepository = trainLineRepository;
        this.trainRepository = trainRepository;
        this.zoneRepository = zoneRepository;
    }

    public void seedDatabase() {
        log.warn("------- Metro Initial -----------");

        // 1. Zones
        ZoneEntity[] zones = {
                ZoneEntity.builder()
                        .type("ZoneA")
                        .ticketPrice(new BigDecimal("2.50"))
                        .build(),
                ZoneEntity.builder()
                        .type("ZoneB")
                        .ticketPrice(new BigDecimal("3.75"))
                        .build(),
                ZoneEntity.builder()
                        .type("ZoneC")
                        .ticketPrice(new BigDecimal("5.00"))
                        .build(),
                ZoneEntity.builder()
                        .type("ZoneD")
                        .ticketPrice(new BigDecimal("7.25"))
                        .build()
        };
        this.zoneRepository.saveAll(Arrays.asList(zones));

        // 2. Trains
        TrainEntity[] trains = {
                TrainEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0200"))
                        .numCars(5)
                        .operational(true)
                        .maxSpeed(120.0)
                        .build(),
                TrainEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0201"))
                        .numCars(8)
                        .operational(true)
                        .maxSpeed(140.0)
                        .build(),
                TrainEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0202"))
                        .numCars(10)
                        .operational(false)
                        .maxSpeed(160.0)
                        .build(),
                TrainEntity.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0203"))
                        .numCars(12)
                        .operational(true)
                        .maxSpeed(180.0)
                        .build()
        };
        this.trainRepository.saveAll(Arrays.asList(trains));


        // 3. Train Lines
        TrainLineEntity[] trainLines = {
                TrainLineEntity.builder()
                        .number(1)
                        .color("Red")
                        .numStations(15)
                        .circular(false)
                        .trainEntities(Arrays.asList(trains[0], trains[1]))  // references A001, A002
                        .build(),
                TrainLineEntity.builder()
                        .number(2)
                        .color("Blue")
                        .numStations(20)
                        .circular(true)
                        .trainEntities(List.of(trains[2]))  // references A003
                        .build(),
                TrainLineEntity.builder()
                        .number(3)
                        .color("Green")
                        .numStations(12)
                        .circular(false)
                        .trainEntities(List.of(trains[3]))  // references A004
                        .build()
        };
        this.trainLineRepository.saveAll(Arrays.asList(trainLines));

        // 4. Train Stations
        TrainStationEntity[] trainStations = {
                TrainStationEntity.builder()
                        .name("Central Station")
                        .capacity(500)
                        .location("Downtown")
                        .multipleLines(true)
                        .inaugurationDate(LocalDate.of(1990, 5, 12))
                        .trainLineEntities(Arrays.asList(trainLines[0], trainLines[1]))
                        .zoneType("ZoneA")
                        .users(Arrays.asList(UUID.randomUUID(), UUID.randomUUID()))
                        .build(),
                TrainStationEntity.builder()
                        .name("North Station")
                        .capacity(300)
                        .location("Uptown")
                        .multipleLines(false)
                        .inaugurationDate(LocalDate.of(2000, 3, 20))
                        .trainLineEntities(List.of(trainLines[2]))
                        .zoneType("ZoneB")
                        .users(List.of(UUID.randomUUID()))
                        .build(),
                TrainStationEntity.builder()
                        .name("East Station")
                        .capacity(400)
                        .location("East Side")
                        .multipleLines(false)
                        .inaugurationDate(LocalDate.of(2010, 7, 15))
                        .trainLineEntities(List.of(trainLines[1]))
                        .zoneType("ZoneC")
                        .users(Arrays.asList(UUID.randomUUID(), UUID.randomUUID(), UUID.randomUUID()))
                        .build(),
                TrainStationEntity.builder()
                        .name("West Station")
                        .capacity(350)
                        .location("West End")
                        .multipleLines(true)
                        .inaugurationDate(LocalDate.of(2015, 11, 5))
                        .trainLineEntities(Arrays.asList(trainLines[0], trainLines[2]))
                        .zoneType("ZoneD")
                        .users(List.of())
                        .build()
        };

        this.trainStationRepository.saveAll(Arrays.asList(trainStations));


        log.warn("-- Metro Station Seeder Done --");
    }

    public void deleteAll() {
        this.trainRepository.deleteAll();
        this.trainStationRepository.deleteAll();
        this.trainLineRepository.deleteAll();
        this.zoneRepository.deleteAll();
    }
}
