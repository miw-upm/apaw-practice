package es.upm.miw.apaw.domain.services.theater;

import es.upm.miw.apaw.domain.models.theater.TheaterPerformance;
import es.upm.miw.apaw.domain.persistenceports.theater.TheaterPerformancePersistence;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TheaterPerformanceServiceTest {

    @Autowired
    private TheaterPerformanceService theaterPerformanceService;

    @MockitoBean
    private TheaterPerformancePersistence theaterPerformancePersistence;

    @Test
    void testFindByMinDate() {
        TheaterPerformance perf = TheaterPerformance.builder()
                .performanceCode("TPER01")
                .performanceTitle("Test Play")
                .performanceDate(LocalDate.of(2026, 12, 1))
                .performanceDurationMinutes(90)
                .performanceTicketPrice(new BigDecimal("25.00"))
                .build();
        BDDMockito.given(this.theaterPerformancePersistence.findByMinDate(Mockito.eq(LocalDate.of(2026, 11, 1))))
                .willReturn(Stream.of(perf));

        Stream<TheaterPerformance> result = this.theaterPerformanceService.findByMinDate(LocalDate.of(2026, 11, 1));
        List<TheaterPerformance> list = result.toList();

        assertThat(list).hasSize(1);
        assertThat(list.getFirst().getPerformanceCode()).isEqualTo("TPER01");
        assertThat(list.getFirst().getPerformanceDate()).isEqualTo(LocalDate.of(2026, 12, 1));
        BDDMockito.then(this.theaterPerformancePersistence).should().findByMinDate(LocalDate.of(2026, 11, 1));
    }

    @Test
    void testFindByMinDate_NoResults() {
        BDDMockito.given(this.theaterPerformancePersistence.findByMinDate(Mockito.any(LocalDate.class)))
                .willReturn(Stream.empty());

        Stream<TheaterPerformance> result = this.theaterPerformanceService.findByMinDate(LocalDate.of(2027, 1, 1));
        List<TheaterPerformance> list = result.toList();

        assertThat(list).isEmpty();
    }
}
