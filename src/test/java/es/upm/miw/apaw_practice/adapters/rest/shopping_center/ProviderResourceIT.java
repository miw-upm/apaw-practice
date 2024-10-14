package es.upm.miw.apaw_practice.adapters.rest.shopping_center;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Provider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class ProviderResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Provider provider =
                new Provider("provider4", "drinks", false);
        this.webTestClient
                .post()
                .uri(ProviderResource.PROVIDERS)
                .body(BodyInserters.fromValue(provider))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Provider.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testCreateConflict() {
        Provider provider =
                new Provider("provider3", "drinks", false);
        this.webTestClient
                .post()
                .uri(ProviderResource.PROVIDERS)
                .body(BodyInserters.fromValue(provider))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.CONFLICT);
    }
}
