package es.upm.miw.apaw.functionaltests.vehicle;

import es.upm.miw.apaw.adapters.resources.vehicle.VehicleResource;
import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class VehicleResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByBrand() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(VehicleResource.VEHICLES)
                        .queryParam("brand", "Benelli")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Vehicle.class)
                .value(vehicles -> {
                    assertThat(vehicles).isNotEmpty();
                    assertThat(vehicles)
                            .extracting(Vehicle::getBrand)
                            .allMatch("Benelli"::equals);
                });
    }

    @Test
    void testFindByBrandNotFound() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(VehicleResource.VEHICLES)
                        .queryParam("brand", "NoBrand")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Vehicle.class)
                .value(vehicles -> assertThat(vehicles).isEmpty());
    }

    @Test
    void testFindByBrandBadRequest() {
        webTestClient.get()
                .uri(VehicleResource.VEHICLES)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void testFindExtraCategoriesByDocumentationName() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(VehicleResource.VEHICLES + VehicleResource.SEARCH + VehicleResource.EXTRA_CATEGORIES)
                        .queryParam("documentationName", "TestDocument")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(extraCategories -> {
                    assertThat(extraCategories).hasSize(1);
                    String jsonList = extraCategories.getFirst();
                    List<String> parsed = List.of("Appearance");
                    assertThat(jsonList).contains("Appearance");
                });
    }
}
