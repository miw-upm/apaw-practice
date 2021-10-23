package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.rest.shop.ShoppingCartResource;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class ActiveIngredientResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindByPharmacyAndByRepetition() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(ActiveIngredientResource.ACTIVEINGREDIENTS + ActiveIngredientResource.SEARCH)
                                .queryParam("q", "pharmacy:kk 1;repeated:true")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }
}
