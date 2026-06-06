package es.upm.miw.apaw.functionaltests.theater;

import es.upm.miw.apaw.BaseTheaterTests;
import es.upm.miw.apaw.adapters.resources.theater.TheaterVenueResource;
import es.upm.miw.apaw.domain.models.theater.TheaterVenue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class TheaterVenueResourceFT extends BaseTheaterTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        webTestClient.get()
                .uri(TheaterVenueResource.VENUES + TheaterVenueResource.VENUE_CODE, "TVEN01")
                .exchange()
                .expectStatus().isOk()
                .expectBody(TheaterVenue.class)
                .value(venue -> {
                    assertThat(venue).isNotNull();
                    assertThat(venue.getVenueCode()).isEqualTo("TVEN01");
                    assertThat(venue.getVenueName()).isEqualTo("Test Theater");
                    assertThat(venue.getVenueCity()).isEqualTo("Madrid");
                    assertThat(venue.getVenueOpen()).isTrue();
                    assertThat(venue.getVenueHalls()).hasSize(2);
                    assertThat(venue.getVenueManager()).isNotNull();
                });
    }

    @Test
    void testRead_NotFound() {
        webTestClient.get()
                .uri(TheaterVenueResource.VENUES + TheaterVenueResource.VENUE_CODE, "NONEXISTENT")
                .exchange()
                .expectStatus().isNotFound();
    }
}
