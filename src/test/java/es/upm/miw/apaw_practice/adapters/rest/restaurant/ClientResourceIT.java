package es.upm.miw.apaw_practice.adapters.rest.restaurant;


import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.restaurant.Client;
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
    void testFindNameByDni(){
        this.webTestClient
                .get()
                .uri(CLIENTS+ID_DNI+NAME,"82912110P")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Client.class)
                .value(client -> assertEquals("Luis",client.getName()));
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
