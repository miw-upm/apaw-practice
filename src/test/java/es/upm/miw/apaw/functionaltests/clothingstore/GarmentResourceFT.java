package es.upm.miw.apaw.functionaltests.clothingstore;

import es.upm.miw.apaw.adapters.mongodb.DatabaseSeeder;
import es.upm.miw.apaw.adapters.resources.clothingstore.GarmentResource;
import es.upm.miw.apaw.domain.models.clothingstore.Garment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class GarmentResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void seed() {
        // 保证每次测试有数据（你在 clothingstoreSeeder 里种了 59.99 和 89.99）
        databaseSeeder.reSeedDatabase();
    }

    @Test
    void testFindByPriceBetween() {
        List<Garment> garments = this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(GarmentResource.GARMENTS)
                        .queryParam("min", "50")
                        .queryParam("max", "100")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Garment.class)
                .returnResult()
                .getResponseBody();

        assertThat(garments).isNotNull();
        if (!garments.isEmpty()) {
            assertThat(garments)
                    .allSatisfy(g -> assertThat(g.getPrice())
                            .isBetween(new BigDecimal("50"), new BigDecimal("100")));
        }

    }
}
