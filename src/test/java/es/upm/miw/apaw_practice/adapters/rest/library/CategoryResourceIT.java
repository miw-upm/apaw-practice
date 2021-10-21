package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.library.CategoryDescriptionUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

@RestTestConfig
class CategoryResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateDescriptions() {
        List<CategoryDescriptionUpdating> categoryDescriptionUpdatingList = List.of();
        this.webTestClient
                .patch()
                .uri(CategoryResource.CATEGORIES)
                .body(BodyInserters.fromValue(categoryDescriptionUpdatingList))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testUpdateDescriptionsNotFound() {
        List<CategoryDescriptionUpdating> categoryDescriptionUpdatingList = List.of(
                new CategoryDescriptionUpdating("xpto", "nop")
        );
        this.webTestClient
                .patch()
                .uri(CategoryResource.CATEGORIES)
                .body(BodyInserters.fromValue(categoryDescriptionUpdatingList))
                .exchange()
                .expectStatus().isNotFound();
    }
}
