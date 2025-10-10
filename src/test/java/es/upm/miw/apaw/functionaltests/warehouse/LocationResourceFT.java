package es.upm.miw.apaw.functionaltests.warehouse;

import es.upm.miw.apaw.adapters.resources.warehouse.LocationResource;
import es.upm.miw.apaw.domain.models.warehouse.Location;
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
class LocationResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadAll() {
        this.webTestClient
                .get()
                .uri(LocationResource.LOCATIONS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Location.class)
                .value(locations -> {
                    assertThat(locations).isNotNull();
                    assertThat(locations).hasSize(3); // según tu WarehouseSeeder
                    assertThat(locations)
                            .extracting(Location::getPosition)
                            .containsExactlyInAnyOrder("Z1-A", "Z2-C", "Y1-F");
                });
    }

}
