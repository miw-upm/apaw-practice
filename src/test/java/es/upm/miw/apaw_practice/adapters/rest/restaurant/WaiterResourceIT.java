package es.upm.miw.apaw_practice.adapters.rest.restaurant;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RestTestConfig
class WaiterResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testSearchBySectionAndCategory(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(WaiterResource.WAITERS+WaiterResource.SEARCHES)
                                .queryParam("q", "section:dining room;category:manager")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Waiter.class)
                .value(waiters -> assertEquals("manager",waiters.get(0).getCategory()))
                .value(waiters -> assertEquals("dining room",waiters.get(0).getSection()));
    }
}
