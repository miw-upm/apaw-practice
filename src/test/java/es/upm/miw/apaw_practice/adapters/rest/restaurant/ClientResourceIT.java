package es.upm.miw.apaw_practice.adapters.rest.restaurant;


import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw_practice.adapters.rest.restaurant.ClientResource.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class ClientResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadCategoryByIdWaiterAndDniClient(){
        this.webTestClient
                .get()
                .uri(CLIENTS+"/42279207D"+WAITERS+"/terrace"+WaiterResource.CATEGORY)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDelete(){
        this.webTestClient
                .delete()
                .uri(CLIENTS + ID_DNI, "test")
                .exchange()
                .expectStatus().isOk();
    }

}
