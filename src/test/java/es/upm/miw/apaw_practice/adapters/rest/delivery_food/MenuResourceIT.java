package es.upm.miw.apaw_practice.adapters.rest.delivery_food;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@RestTestConfig
class MenuResourceIT {

    @Autowired
    private WebTestClient webTestClient;
    @Test
    void testDelete() {
        this.webTestClient
                .delete()
                .uri(MenuResource.MENU + MenuResource.NAME_ID, "Dessert Extravaganza")
                .exchange()
                .expectStatus().isOk();
    }
}
