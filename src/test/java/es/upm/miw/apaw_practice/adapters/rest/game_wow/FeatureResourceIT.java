package es.upm.miw.apaw_practice.adapters.rest.game_wow;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RestTestConfig
public class FeatureResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Feature feature =  Feature.builder()
                .part("Head")
                .spellPower(158)
                .meleeAtack(120)
                .temple(80)
                .extraSpell("Use: Restores 2000 mana")
                .build();
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
                .body(BodyInserters.fromValue(0))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testFindByDescriptionBoss() {
        this.webTestClient
                .get()
                .uri(uriBuilder -> uriBuilder.path(FeatureResource.GAMEWOW_FEATURES + FeatureResource.SEARCH)
                        .queryParam("q", "descriptionBoss:Lord Marrowgal")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .value(partDtoList -> partDtoList.get(0), equalTo("[\"Trinket\",\"Neck\"]"))
                .value(partDtoList -> assertTrue(partDtoList.size() > 0));
    }
}
