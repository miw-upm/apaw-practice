package es.upm.miw.apaw;

import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterArtistRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterHallRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterPerformanceRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterSeeder;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterVenueRepository;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterArtistEntity;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterHallEntity;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterPerformanceEntity;
import es.upm.miw.apaw.adapters.mongodb.theater.entities.TheaterVenueEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public abstract class BaseTheaterTests {

    protected TheaterArtistEntity[] artists;
    protected TheaterHallEntity[] halls;
    protected TheaterVenueEntity[] venues;
    protected TheaterPerformanceEntity[] performances;

    @Autowired
    protected TheaterArtistRepository theaterArtistRepository;

    @Autowired
    protected TheaterHallRepository theaterHallRepository;

    @Autowired
    protected TheaterVenueRepository theaterVenueRepository;

    @Autowired
    protected TheaterPerformanceRepository theaterPerformanceRepository;

    @BeforeEach
    void beforeEach() {
        artists = new TheaterArtistEntity[]{
                TheaterArtistEntity.builder()
                        .id(UUID.randomUUID())
                        .artistCode("TART01")
                        .artistFullName("Alice Performer")
                        .artistBirthDate(LocalDate.of(1992, 4, 10))
                        .artistFee(new BigDecimal("800.00"))
                        .artistActive(true)
                        .build(),
                TheaterArtistEntity.builder()
                        .id(UUID.randomUUID())
                        .artistCode("TART02")
                        .artistFullName("Bob Dancer")
                        .artistBirthDate(LocalDate.of(1988, 9, 25))
                        .artistFee(new BigDecimal("950.00"))
                        .artistActive(true)
                        .build()
        };

        halls = new TheaterHallEntity[]{
                TheaterHallEntity.builder()
                        .id(UUID.randomUUID())
                        .hallCode("THAL01")
                        .hallName("Theater Hall A")
                        .hallCapacity(300)
                        .hallAccessible(true)
                        .build(),
                TheaterHallEntity.builder()
                        .id(UUID.randomUUID())
                        .hallCode("THAL02")
                        .hallName("Theater Hall B")
                        .hallCapacity(100)
                        .hallAccessible(false)
                        .build()
        };

        venues = new TheaterVenueEntity[]{
                TheaterVenueEntity.builder()
                        .id(UUID.randomUUID())
                        .venueCode("TVEN01")
                        .venueName("Test Theater")
                        .venueCity("Madrid")
                        .venueOpen(true)
                        .venueCreatedAt(LocalDateTime.of(2023, 1, 1, 0, 0))
                        .venueHalls(List.of(halls[0], halls[1]))
                        .venueManagerId(UUID.randomUUID())
                        .build()
        };

        performances = new TheaterPerformanceEntity[]{
                TheaterPerformanceEntity.builder()
                        .id(UUID.randomUUID())
                        .performanceCode("TPER01")
                        .performanceTitle("Test Play")
                        .performanceDate(LocalDate.of(2026, 12, 1))
                        .performanceDurationMinutes(90)
                        .performanceTicketPrice(new BigDecimal("25.00"))
                        .performanceHall(halls[0])
                        .performanceArtists(new HashSet<>(List.of(artists[0])))
                        .build()
        };

        theaterArtistRepository.saveAll(Arrays.asList(artists));
        theaterHallRepository.saveAll(Arrays.asList(halls));
        theaterVenueRepository.saveAll(Arrays.asList(venues));
        theaterPerformanceRepository.saveAll(Arrays.asList(performances));
    }

    @AfterEach
    void afterEach() {
        theaterPerformanceRepository.deleteAll();
        theaterVenueRepository.deleteAll();
        theaterHallRepository.deleteAll();
        theaterArtistRepository.deleteAll();
    }
}
