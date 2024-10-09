package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static es.upm.miw.apaw_practice.adapters.rest.bank.ClientResource.*;
import static org.junit.jupiter.api.Assertions.*;

@RestTestConfig
public class ClientResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testFindById() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CLIENTS + SEARCH)
                                .queryParam("dni", "11111111A")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Client.class)
                .value(Assertions::assertNotNull)
                .value(client -> {
                    assertEquals("Client1", client.getName());
                    assertEquals("Client1", client.getSurname());
                    assertEquals(111111111, client.getPhoneNumber());
                    assertNotNull(client.getInvestmentFunds());
                });
    }

    @Test
    void testFindByIdNotFound() {
        this.webTestClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path(CLIENTS + SEARCH)
                                .queryParam("dni", "000000000")
                                .build())
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdateNameNotFound() {
        this.webTestClient
                .patch()
                .uri(CLIENTS + DNI, "kk")
                .body(BodyInserters.fromValue("NewName"))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate() {
        this.webTestClient
                .patch()
                .uri(CLIENTS + DNI, "33333333C")
                .body(BodyInserters.fromValue("NewName"))
                .exchange()
                .expectStatus().isOk()
                .expectBody(Client.class)
                .value(Assertions::assertNotNull)
                .value(client -> assertEquals("NewName", client.getName()));
    }
}
