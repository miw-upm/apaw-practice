package es.upm.miw.apaw_practice.adapters.rest.pharmacy;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.adapters.rest.shop.ArticleResource;
import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import es.upm.miw.apaw_practice.domain.models.shop.Article;
import es.upm.miw.apaw_practice.domain.models.shop.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

@RestTestConfig
public class DrugResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testGetDrugs() {
        this.webTestClient
                .get()
                .uri(DrugResource.DRUGS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Drug.class);
    }
}
