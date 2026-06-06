package es.upm.miw.apaw.adapters.mongodb.theater.daos;

import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterArtistEntity;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterHallEntity;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterPerformanceEntity;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterVenueEntity;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@Repository
@Profile({"dev", "test"})
@Log4j2
public class TheaterSeeder {

    private final TheaterArtistRepository theaterArtistRepository;
    private final TheaterHallRepository theaterHallRepository;
    private final TheaterVenueRepository theaterVenueRepository;
    private final TheaterPerformanceRepository theaterPerformanceRepository;

    @Autowired
    public TheaterSeeder(
            TheaterArtistRepository theaterArtistRepository,
            TheaterHallRepository theaterHallRepository,
            TheaterVenueRepository theaterVenueRepository,
            TheaterPerformanceRepository theaterPerformanceRepository) {
        this.theaterArtistRepository = theaterArtistRepository;
        this.theaterHallRepository = theaterHallRepository;
        this.theaterVenueRepository = theaterVenueRepository;
        this.theaterPerformanceRepository = theaterPerformanceRepository;
    }

    public void seedDatabase() {
        log.warn("------- Theater Initial Load -----------");

        if (theaterArtistRepository.count() > 0) {
            log.warn("------- Theater already seeded -----------");
            return;
        }

        TheaterArtistEntity[] artists = {
                TheaterArtistEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000001"))
                        .artistCode("ART001")
                        .artistFullName("Maria Garcia")
                        .artistBirthDate(LocalDate.of(1990, 3, 15))
                        .artistFee(new BigDecimal("1500.00"))
                        .artistActive(true)
                        .build(),
                TheaterArtistEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000002"))
                        .artistCode("ART002")
                        .artistFullName("John Smith")
                        .artistBirthDate(LocalDate.of(1985, 7, 22))
                        .artistFee(new BigDecimal("1200.00"))
                        .artistActive(true)
                        .build(),
                TheaterArtistEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000003"))
                        .artistCode("ART003")
                        .artistFullName("Ana Lopez")
                        .artistBirthDate(LocalDate.of(1995, 11, 8))
                        .artistFee(new BigDecimal("1000.00"))
                        .artistActive(false)
                        .build()
        };
        theaterArtistRepository.saveAll(Arrays.asList(artists));

        TheaterHallEntity[] halls = {
                TheaterHallEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000011"))
                        .hallCode("HAL001")
                        .hallName("Main Stage")
                        .hallCapacity(500)
                        .hallAccessible(true)
                        .build(),
                TheaterHallEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000012"))
                        .hallCode("HAL002")
                        .hallName("Studio Theater")
                        .hallCapacity(150)
                        .hallAccessible(true)
                        .build(),
                TheaterHallEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000013"))
                        .hallCode("HAL003")
                        .hallName("Black Box")
                        .hallCapacity(80)
                        .hallAccessible(false)
                        .build()
        };
        theaterHallRepository.saveAll(Arrays.asList(halls));

        TheaterVenueEntity[] venues = {
                TheaterVenueEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000021"))
                        .venueCode("VEN001")
                        .venueName("Gran Teatro")
                        .venueCity("Madrid")
                        .venueOpen(true)
                        .venueCreatedAt(LocalDateTime.of(2020, 1, 10, 0, 0))
                        .venueHalls(List.of(halls[0], halls[1]))
                        .venueManagerId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"))
                        .build(),
                TheaterVenueEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000022"))
                        .venueCode("VEN002")
                        .venueName("Teatro Cultural")
                        .venueCity("Barcelona")
                        .venueOpen(true)
                        .venueCreatedAt(LocalDateTime.of(2021, 6, 20, 0, 0))
                        .venueHalls(List.of(halls[2]))
                        .venueManagerId(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                        .build()
        };
        theaterVenueRepository.saveAll(Arrays.asList(venues));

        TheaterPerformanceEntity[] performances = {
                TheaterPerformanceEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000031"))
                        .performanceCode("PER001")
                        .performanceTitle("Romeo and Juliet")
                        .performanceDate(LocalDate.of(2026, 9, 15))
                        .performanceDurationMinutes(120)
                        .performanceTicketPrice(new BigDecimal("45.00"))
                        .performanceHall(halls[0])
                        .performanceArtists(new HashSet<>(List.of(artists[0], artists[1])))
                        .build(),
                TheaterPerformanceEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000032"))
                        .performanceCode("PER002")
                        .performanceTitle("The Magic Flute")
                        .performanceDate(LocalDate.of(2026, 10, 5))
                        .performanceDurationMinutes(150)
                        .performanceTicketPrice(new BigDecimal("55.00"))
                        .performanceHall(halls[0])
                        .performanceArtists(new HashSet<>(List.of(artists[1])))
                        .build(),
                TheaterPerformanceEntity.builder()
                        .id(UUID.fromString("bbbbbbbb-cccc-dddd-eeee-ffff00000033"))
                        .performanceCode("PER003")
                        .performanceTitle("Contemporary Dance Night")
                        .performanceDate(LocalDate.of(2026, 11, 1))
                        .performanceDurationMinutes(90)
                        .performanceTicketPrice(new BigDecimal("30.00"))
                        .performanceHall(halls[2])
                        .performanceArtists(new HashSet<>(List.of(artists[0], artists[2])))
                        .build()
        };
        theaterPerformanceRepository.saveAll(Arrays.asList(performances));

        log.warn("------- Theater seeding completed -----------");
    }

    public void deleteAll() {
        theaterPerformanceRepository.deleteAll();
        theaterVenueRepository.deleteAll();
        theaterHallRepository.deleteAll();
        theaterArtistRepository.deleteAll();
    }
}
