package es.upm.miw.apaw.functionaltests.warehouse;

import es.upm.miw.apaw.adapters.resources.warehouse.LocationResource;
import es.upm.miw.apaw.domain.models.warehouse.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class LocationResourceFT {

    @Autowired
    private WebTestClient webTestClient;


    @Test
    void testReadAll() {
        this.webTestClient.get()
                .uri(LocationResource.LOCATIONS) // sin BASE_URL
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Location.class)
                .value(list -> assertThat(list).isNotEmpty());
    }

    @Test
    void testReadByPositionExisting() {
        this.webTestClient.get()
                .uri(LocationResource.LOCATIONS + LocationResource.POSITION, "A1") // usa la constante del resource
                .exchange()
                .expectStatus().isOk()
                .expectBody(Location.class)
                .value(loc -> assertThat(loc.getPosition()).isEqualTo("A1"));
    }

    @Test
    void testReadByPositionNotFound() {
        this.webTestClient.get()
                .uri(LocationResource.LOCATIONS + LocationResource.POSITION, "Z9")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateAvailabilityExisting() {
        this.webTestClient.patch()
                .uri(LocationResource.LOCATIONS + LocationResource.AVAILABILITY, "A1")
                .bodyValue(Map.of("availability", false))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Location.class)
                .value(loc -> assertThat(loc.getAvailability()).isFalse());
    }

    @Test
    void testUpdateAvailabilityNotFound() {
        this.webTestClient.patch()
                .uri(LocationResource.LOCATIONS + LocationResource.AVAILABILITY, "ZZ")
                .bodyValue(Map.of("availability", true))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDeleteByPosition() {
        this.webTestClient.delete()
                .uri(LocationResource.LOCATIONS + LocationResource.POSITION, "B1")
                .exchange()
                .expectStatus().isOk();

        this.webTestClient.get()
                .uri(LocationResource.LOCATIONS + LocationResource.POSITION, "B1")
                .exchange()
                .expectStatus().isNotFound();
    }

}