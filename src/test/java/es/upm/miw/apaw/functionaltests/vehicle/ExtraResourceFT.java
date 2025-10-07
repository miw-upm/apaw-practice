package es.upm.miw.apaw.functionaltests.vehicle;

import es.upm.miw.apaw.adapters.resources.vehicle.ExtraResource;
import es.upm.miw.apaw.domain.models.vehicle.Extra;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class ExtraResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdatePrices() {
        List<Extra> updates = List.of(
                Extra.builder()
                        .category("Safety")
                        .description("Airbags")
                        .price(new BigDecimal("999.99"))
                        .build()
        );

        webTestClient.patch()
                .uri(ExtraResource.EXTRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdatePricesNotFound() {
        List<Extra> updates = List.of(
                Extra.builder()
                        .category("Fake")
                        .description("Fake")
                        .price(BigDecimal.TEN)
                        .build()
        );

        webTestClient.patch()
                .uri(ExtraResource.EXTRAS)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updates)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testDelete() {
        UUID id = UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"); // Airbags del seeder
        webTestClient.delete()
                .uri(ExtraResource.EXTRAS + ExtraResource.EXTRA_ID, id)
                .exchange()
                .expectStatus().isOk();
    }
}
