package es.upm.miw.apaw_practice.adapters.rest.restaurant;


import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class ClientResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadCategoryByIdWaiterAndDniClient(){
        this.webTestClient
                .get()
                .uri(ClientResource.CLIENTS+"/42279207D"+ClientResource.WAITERS+"/terrace"+WaiterResource.CATEGORY)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Waiter.class)
                .value(waiters -> assertEquals("employee",waiters.get(0).getCategory()));
    }

}
