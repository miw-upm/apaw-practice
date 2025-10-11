package es.upm.miw.apaw.functionaltests.winery;

import es.upm.miw.apaw.adapters.resources.winery.WineResource;
import es.upm.miw.apaw.domain.models.winery.Wine;
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

import static es.upm.miw.apaw.adapters.resources.winery.WineResource.ID;
import static es.upm.miw.apaw.adapters.resources.winery.WineResource.WINES;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
public class WineEntityResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testRead() {
        webTestClient.get()
                .uri(WINES + ID, UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Wine.class)
                .value(wine -> {
                    assertThat(wine).isNotNull();
                    assertThat(wine.getPrice()).isEqualByComparingTo("18.90");
                    assertThat(wine.getName()).isEqualTo("Merlot");
                    assertThat(wine.getYear()).isEqualTo(2019);
                    assertThat(wine.getAlcoholPercentage()).isEqualTo(14.00);
                });
    }

    @Test
    void testDelete() {
        webTestClient.delete()
                .uri(WINES + ID, UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdatePrice() {
        List<Wine> wineUpdates = List.of(
                Wine.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0003"))
                        .price(new BigDecimal("23.00"))
                        .build()
        );

        webTestClient.patch()
                .uri(WineResource.WINES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(wineUpdates)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdatePriceNotFound() {
        List<Wine> wineUpdates = List.of(
                Wine.builder()
                        .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
                        .price(new BigDecimal("23.00"))
                        .build()
        );

        webTestClient.patch()
                .uri(WineResource.WINES)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(wineUpdates)
                .exchange()
                .expectStatus().isNotFound();
    }

}
