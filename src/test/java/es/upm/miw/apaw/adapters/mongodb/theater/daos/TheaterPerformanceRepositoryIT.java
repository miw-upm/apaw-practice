package es.upm.miw.apaw.adapters.mongodb.theater.daos;

import es.upm.miw.apaw.BaseTheaterTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class TheaterPerformanceRepositoryIT extends BaseTheaterTests {

    @Autowired
    private TheaterPerformanceRepository theaterPerformanceRepository;

    @Test
    void testFindByPerformanceCode() {
        assertThat(theaterPerformanceRepository.findByPerformanceCode(performances[0].getPerformanceCode()))
                .isPresent()
                .hasValueSatisfying(e -> {
                    assertThat(e.getPerformanceCode()).isEqualTo(performances[0].getPerformanceCode());
                    assertThat(e.getPerformanceTitle()).isEqualTo(performances[0].getPerformanceTitle());
                    assertThat(e.getPerformanceDate()).isEqualTo(performances[0].getPerformanceDate());
                    assertThat(e.getPerformanceDurationMinutes()).isEqualTo(performances[0].getPerformanceDurationMinutes());
                    assertThat(e.getPerformanceTicketPrice()).isEqualTo(performances[0].getPerformanceTicketPrice());
                    assertThat(e.getPerformanceHall()).isNotNull();
                    assertThat(e.getPerformanceArtists()).hasSize(1);
                });
    }

    @Test
    void testFindByPerformanceCode_NotFound() {
        assertThat(theaterPerformanceRepository.findByPerformanceCode("NONEXISTENT")).isEmpty();
    }

    @Test
    void testDeleteByPerformanceCode() {
        int deleted = theaterPerformanceRepository.deleteByPerformanceCode(performances[0].getPerformanceCode());
        assertThat(deleted).isEqualTo(1);
        assertThat(theaterPerformanceRepository.findByPerformanceCode(performances[0].getPerformanceCode())).isEmpty();
    }

    @Test
    void testFindAll() {
        assertThat(theaterPerformanceRepository.findAll()).hasSizeGreaterThanOrEqualTo(1);
    }
}
