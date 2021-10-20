package es.upm.miw.apaw_practice.adapters.rest.restaurant;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RestTestConfig
class WaiterResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate(){
        Waiter waiter = new Waiter("terrace","employee");
        this.webTestClient
                .post()
                .uri(WaiterResource.WAITERS)
                .body(BodyInserters.fromValue(waiter))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Waiter.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testFindSection(){
        this.webTestClient
                .get()
                .uri(WaiterResource.WAITERS+WaiterResource.SECTION)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Waiter.class)
                .value(waiters -> assertEquals("terrace",waiters.get(0).getSection()));
    }

    @Test
    void testFindByNumberTable(){
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                                uriBuilder.path(WaiterResource.WAITERS+WaiterResource.SEARCH)
                                        .queryParam("q", "number:2")
                                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Waiter.class)
                .value(waiters -> assertEquals(waiters.get(0).getSection(),"terrace"))
                .value(waiters -> assertEquals(waiters.get(1).getSection(),"dining room"))
                .value(waiters -> assertTrue(waiters.size() == 2));
    }
}
