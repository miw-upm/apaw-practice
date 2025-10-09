package es.upm.miw.apaw.functionaltests.vehicle;

import es.upm.miw.apaw.adapters.resources.vehicle.VehicleResource;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.vehicle.Vehicle;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class VehicleResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

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

    @Test
    void testFindUserMobilesByEngineType() {
        // It is important to simulate userRestClient.
        // The first test I did was with apaw-user running on local and they executed successfully, but GitHub Actions cannot access the apaw-user URL.
        BDDMockito.given(this.userRestClient.readById(any(UUID.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(invocation.getArgument(0)).mobile("123456789").firstName("mock").build());

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(VehicleResource.VEHICLES + VehicleResource.SEARCH + VehicleResource.USER_MOBILES)
                        .queryParam("engineType", "Diesel")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(extraCategories -> {
                    assertThat(extraCategories).hasSize(1);
                    String jsonList = extraCategories.getFirst();
                    List<String> parsed = List.of("123456789");
                    assertThat(jsonList).contains("123456789");
                });
    }
}
