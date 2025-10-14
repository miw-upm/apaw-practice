package es.upm.miw.apaw.functionaltests.apiary;

import es.upm.miw.apaw.adapters.resources.apiary.ApiaryResource;
import es.upm.miw.apaw.domain.models.apiary.Apiary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ApiaryResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByLocationReturnsApiaries() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiaryResource.APIARIES)
                        .queryParam("location", "Burgos")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Apiary.class)
                .value(apiaries -> assertThat(apiaries)
                        .isNotEmpty()
                        .allMatch(apiary -> "Burgos".equals(apiary.getLocation())));
    }

    @Test
    void testFindByLocationReturnsEmptyWhenNotFound() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiaryResource.APIARIES)
                        .queryParam("location", "NoExiste")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Apiary.class)
                .value(apiaries -> assertThat(apiaries).isEmpty());
    }

    @Test
    void testFindLocationsByShippingAddressReturnsLocations() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiaryResource.APIARIES + "/locations-by-shipping")
                        .queryParam("shippingAddress", "Calle Mayor 10, Madrid")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Object.class)
                .value(locations -> assertThat(locations)
                        .isNotEmpty()
                        .contains("Burgos"));
    }

    @Test
    void testFindLocationsByShippingAddressReturnsEmptyWhenNotFound() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiaryResource.APIARIES + "/locations-by-shipping")
                        .queryParam("shippingAddress", "No existe 123")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Object.class)
                .value(locations -> assertThat(locations).isEmpty());
    }

    @Test
    void testFindLocationsByShippingAddressV2() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiaryResource.APIARIES + "/locations-by-shipping")
                        .queryParam("shippingAddress", "Calle Mayor 10, Madrid")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0]").isEqualTo("Burgos");
    }
}
