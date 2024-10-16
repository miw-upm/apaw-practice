package es.upm.miw.apaw_practice.adapters.rest.gun_store;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.gun_store.AccesoryPriceUpdating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;

@RestTestConfig
public class AccesoryResourceIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdatePrice(){
        AccesoryPriceUpdating accesoryPriceUpdating = new AccesoryPriceUpdating(2, new BigDecimal("49.95"));
        this.webTestClient
                .patch()
                .uri(AccesoryResource.ACCESORIES)
                .body(BodyInserters.fromValue(accesoryPriceUpdating))
                .exchange()
                .expectStatus().isOk()
        ;
    }
}
