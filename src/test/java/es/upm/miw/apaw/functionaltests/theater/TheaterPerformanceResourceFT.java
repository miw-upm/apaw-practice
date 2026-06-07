package es.upm.miw.apaw.functionaltests.theater;

import es.upm.miw.apaw.BaseTheaterTests;
import es.upm.miw.apaw.adapters.resources.theater.TheaterPerformanceResource;
import es.upm.miw.apaw.domain.models.theater.TheaterPerformance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TheaterPerformanceResourceFT extends BaseTheaterTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByMinDate() {
        webTestClient.get()
                .uri(TheaterPerformanceResource.PERFORMANCES + TheaterPerformanceResource.SEARCH + "?minDate=2026-11-01")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TheaterPerformance.class)
                .value(performances -> {
                    assertThat(performances).hasSize(1);
                    assertThat(performances.getFirst().getPerformanceCode()).isEqualTo("TPER01");
                    assertThat(performances.getFirst().getPerformanceDate()).isEqualTo(LocalDate.of(2026, 12, 1));
                });
    }

    @Test
    void testFindByMinDate_NoResults() {
        webTestClient.get()
                .uri(TheaterPerformanceResource.PERFORMANCES + TheaterPerformanceResource.SEARCH + "?minDate=2027-01-01")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TheaterPerformance.class)
                .value(performances -> assertThat(performances).isEmpty());
    }

    @Test
    void testFindByMinDate_AllPerformances() {
        webTestClient.get()
                .uri(TheaterPerformanceResource.PERFORMANCES + TheaterPerformanceResource.SEARCH + "?minDate=2026-01-01")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TheaterPerformance.class)
                .value(performances -> {
                    assertThat(performances).hasSize(1);
                    assertThat(performances.getFirst().getPerformanceCode()).isEqualTo("TPER01");
                });
    }
}
