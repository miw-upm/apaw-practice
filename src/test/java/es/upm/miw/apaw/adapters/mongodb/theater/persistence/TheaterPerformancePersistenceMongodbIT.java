package es.upm.miw.apaw.adapters.mongodb.theater.persistence;

import es.upm.miw.apaw.BaseTheaterTests;
import es.upm.miw.apaw.adapters.mongodb.theater.daos.TheaterPerformanceRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.theater.TheaterArtist;
import es.upm.miw.apaw.domain.models.theater.TheaterHall;
import es.upm.miw.apaw.domain.models.theater.TheaterPerformance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TheaterPerformancePersistenceMongodbIT extends BaseTheaterTests {

    @Autowired
    private TheaterPerformancePersistenceMongodb theaterPerformancePersistenceMongodb;

    @Autowired
    private TheaterPerformanceRepository theaterPerformanceRepository;

    @Test
    void testCreateAndRead() {
        TheaterPerformance perf = TheaterPerformance.builder()
                .performanceCode("TPPCR")
                .performanceTitle("Create Test Play")
                .performanceDate(LocalDate.of(2027, 1, 1))
                .performanceDurationMinutes(120)
                .performanceTicketPrice(new BigDecimal("35.00"))
                .performanceHall(halls[0].toTheaterHall())
                .performanceArtists(Set.of(artists[0].toTheaterArtist()))
                .build();
        theaterPerformancePersistenceMongodb.create(perf);
        TheaterPerformance read = theaterPerformancePersistenceMongodb.read("TPPCR");
        assertThat(read.getPerformanceCode()).isEqualTo("TPPCR");
        assertThat(read.getPerformanceTitle()).isEqualTo("Create Test Play");
        assertThat(read.getPerformanceDate()).isEqualTo(LocalDate.of(2027, 1, 1));
        assertThat(read.getPerformanceDurationMinutes()).isEqualTo(120);
        assertThat(read.getPerformanceTicketPrice()).isEqualTo(new BigDecimal("35.00"));
        assertThat(read.getPerformanceHall()).isNotNull();
        assertThat(read.getPerformanceArtists()).hasSize(1);
        theaterPerformanceRepository.deleteByPerformanceCode("TPPCR");
    }

    @Test
    void testRead_NotFound() {
        assertThrows(NotFoundException.class, () -> theaterPerformancePersistenceMongodb.read("NONEXISTENT"));
    }

    @Test
    void testUpdate() {
        TheaterPerformance perf = TheaterPerformance.builder()
                .performanceCode("TPPUP")
                .performanceTitle("Original Play")
                .performanceDate(LocalDate.of(2027, 2, 1))
                .performanceDurationMinutes(90)
                .performanceTicketPrice(new BigDecimal("20.00"))
                .performanceHall(halls[0].toTheaterHall())
                .performanceArtists(Set.of(artists[0].toTheaterArtist()))
                .build();
        theaterPerformancePersistenceMongodb.create(perf);
        perf.setPerformanceTitle("Updated Play");
        perf.setPerformanceTicketPrice(new BigDecimal("25.00"));
        TheaterPerformance updated = theaterPerformancePersistenceMongodb.update("TPPUP", perf);
        assertThat(updated.getPerformanceTitle()).isEqualTo("Updated Play");
        assertThat(updated.getPerformanceTicketPrice()).isEqualTo(new BigDecimal("25.00"));
        theaterPerformanceRepository.deleteByPerformanceCode("TPPUP");
    }

    @Test
    void testUpdate_NotFound() {
        TheaterPerformance perf = TheaterPerformance.builder()
                .performanceCode("NONEXISTENT")
                .performanceTitle("No Play")
                .performanceDate(LocalDate.of(2027, 3, 1))
                .performanceDurationMinutes(60)
                .performanceTicketPrice(new BigDecimal("10.00"))
                .performanceHall(halls[0].toTheaterHall())
                .performanceArtists(Set.of())
                .build();
        assertThrows(NotFoundException.class, () -> theaterPerformancePersistenceMongodb.update("NONEXISTENT", perf));
    }

    @Test
    void testReadAll() {
        long initial = theaterPerformancePersistenceMongodb.readAll().count();
        TheaterPerformance perf = TheaterPerformance.builder()
                .performanceCode("TPPDA")
                .performanceTitle("ReadAll Play")
                .performanceDate(LocalDate.of(2027, 4, 1))
                .performanceDurationMinutes(75)
                .performanceTicketPrice(new BigDecimal("15.00"))
                .performanceHall(halls[0].toTheaterHall())
                .performanceArtists(Set.of())
                .build();
        theaterPerformancePersistenceMongodb.create(perf);
        assertThat(theaterPerformancePersistenceMongodb.readAll().count()).isEqualTo(initial + 1);
        theaterPerformanceRepository.deleteByPerformanceCode("TPPDA");
    }

    @Test
    void testExistsByPerformanceCode() {
        assertThat(theaterPerformancePersistenceMongodb.existsByPerformanceCode(performances[0].getPerformanceCode())).isTrue();
        assertThat(theaterPerformancePersistenceMongodb.existsByPerformanceCode("NONEXISTENT")).isFalse();
    }

    @Test
    void testFindArtistsByPerformanceCode() {
        var artists = theaterPerformancePersistenceMongodb.findArtistsByPerformanceCode(performances[0].getPerformanceCode()).toList();
        assertThat(artists).hasSize(1);
    }

    @Test
    void testFindArtistsByPerformanceCode_NotFound() {
        assertThrows(NotFoundException.class,
                () -> theaterPerformancePersistenceMongodb.findArtistsByPerformanceCode("NONEXISTENT"));
    }

    @Test
    void testFindByMinDate() {
        LocalDate minDate = LocalDate.of(2026, 11, 1);
        var results = theaterPerformancePersistenceMongodb.findByMinDate(minDate).toList();
        assertThat(results).hasSize(1);
        assertThat(results.getFirst().getPerformanceCode()).isEqualTo("TPER01");
        assertThat(results.getFirst().getPerformanceDate()).isEqualTo(LocalDate.of(2026, 12, 1));
    }

    @Test
    void testFindByMinDate_NoResults() {
        LocalDate minDate = LocalDate.of(2027, 1, 1);
        var results = theaterPerformancePersistenceMongodb.findByMinDate(minDate).toList();
        assertThat(results).isEmpty();
    }

    @Test
    void testFindByMinDate_AllPerformances() {
        LocalDate minDate = LocalDate.of(2026, 1, 1);
        var results = theaterPerformancePersistenceMongodb.findByMinDate(minDate).toList();
        assertThat(results).hasSize(1);
        assertThat(results.getFirst().getPerformanceCode()).isEqualTo("TPER01");
    }

    @Test
    void testPerformanceToHallUnidirectionalRelationship() {
        TheaterPerformance read = theaterPerformancePersistenceMongodb.read(performances[0].getPerformanceCode());
        TheaterHall hall = read.getPerformanceHall();
        assertThat(hall.getHallCode()).isNotNull();
        assertThat(hall.getHallName()).isNotNull();
        assertThat(hall.getHallCapacity()).isNotNull();
        assertThat(hall.getHallAccessible()).isNotNull();
    }

    @Test
    void testPerformanceToArtistUnidirectionalRelationship() {
        TheaterPerformance read = theaterPerformancePersistenceMongodb.read(performances[0].getPerformanceCode());
        Set<TheaterArtist> artistSet = read.getPerformanceArtists();
        assertThat(artistSet).isNotEmpty();
        artistSet.forEach(artist -> {
            assertThat(artist.getArtistCode()).isNotNull();
            assertThat(artist.getArtistFullName()).isNotNull();
            assertThat(artist.getArtistBirthDate()).isNotNull();
            assertThat(artist.getArtistFee()).isNotNull();
            assertThat(artist.getArtistActive()).isNotNull();
        });
    }
}
