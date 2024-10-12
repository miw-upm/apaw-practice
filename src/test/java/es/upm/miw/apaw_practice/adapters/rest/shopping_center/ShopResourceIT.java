package es.upm.miw.apaw_practice.adapters.rest.shopping_center;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.shopping_center.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class ShopResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindAllShops() {
        this.webTestClient
                .get()
                .uri(ShopResource.SHOPS)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Shop.class)
                .value(shops -> assertFalse(shops.isEmpty()))
                .value(shops -> assertEquals("dir1", shops.get(0).getAddress()));
    }
}
