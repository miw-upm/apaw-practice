package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.rest.shop.ArticleResource;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.List;

@RestTestConfig
public class FeatureResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Feature feature = new Feature("Head", 158, 120, 80, "Use: Restores 2000 mana");
        this.webTestClient
                .post()
                .uri(FeatureResource.GAMEWOW_FEATURES)
                .body(BodyInserters.fromValue(feature))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Feature.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testupdateTemples() {
        this.webTestClient
                .patch()
                .uri(FeatureResource.GAMEWOW_FEATURES)
                .body(BodyInserters.fromValue(200))
                .exchange()
                .expectStatus().isOk();
    }
}
