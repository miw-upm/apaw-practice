package es.upm.miw.apaw.functionaltests.airport;

import es.upm.miw.apaw.adapters.resources.airport.FlightResource;
import es.upm.miw.apaw.domain.models.airport.Flight;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class FlightResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        webTestClient.get()
                .uri(FlightResource.FLIGHTS + "/" + UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Flight.class)
                .value(read -> assertThat(read).isNotNull());
    }

    @Test
    void testReadNotFound() {
        webTestClient.get()
                .uri(FlightResource.FLIGHTS + "/" + UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0000"))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }
}
