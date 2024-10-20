package es.upm.miw.apaw_practice.adapters.rest.movies;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.movies.Studio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class StudioResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetStudioByName() {
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri(StudioResource.STUDIOS + "/{name}", "Warner Bros")
                .exchange();

        response.expectStatus().isOk();
        response.expectBody(Studio.class).value(studio -> {
            assertEquals("Warner Bros", studio.getName());
            assertEquals(LocalDate.of(1923, 4, 4), studio.getFoundedDate());
            assertEquals(new BigDecimal("10000000000"), studio.getMarketCapitalization());
        });
    }

    @Test
    void testGetStudioByNameNotFound() {
        webTestClient
                .get()
                .uri(StudioResource.STUDIOS + "/{name}", "Nonexistent Studio")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetMarketCapitalizationSumDifferentStudios() {
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri(StudioResource.STUDIOS + "/award-category/{category}/market-capitalization-sum", "Best Picture")
                .exchange();

        response.expectStatus().isOk();
        response.expectBody(BigDecimal.class).value(sum -> {
            assertEquals(new BigDecimal("650000000"), sum);
        });
    }

    @Test
    void testGetMarketCapitalizationSumByAwardCategoryDuplicated() {
        WebTestClient.ResponseSpec response = webTestClient
                .get()
                .uri(StudioResource.STUDIOS + "/award-category/{category}/market-capitalization-sum", "Best Director")
                .exchange();

        response.expectStatus().isOk();
        response.expectBody(BigDecimal.class).value(sum -> {
            assertEquals(new BigDecimal("10000000000"), sum);
        });
    }

    @Test
    void testGetMarketCapitalizationSumByAwardCategoryNotExist() {
        webTestClient
                .get()
                .uri(StudioResource.STUDIOS + "/award-category/{category}/market-capitalization-sum", "Best Catering")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class).isEqualTo(BigDecimal.ZERO);
    }
}
