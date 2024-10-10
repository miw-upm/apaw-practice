package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static es.upm.miw.apaw_practice.adapters.rest.bank.BranchOfficeResource.*;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RestTestConfig
class BranchOfficeResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        Client client1 = new Client("11111111A", "Client1", "Client1", 111111111, "email1@example.com", emptyList());
        Client client2 = new Client("22222222B", "Client2", "Client2", 222222222, "email2@example.com", emptyList());
        List<Client> clients = Arrays.asList(client1, client2);
        BranchOffice branchOffice = new BranchOffice("Building1", 10,10,clients);
        this.webTestClient
                .post()
                .uri(OFFICES)
                .body(BodyInserters.fromValue(branchOffice))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BranchOffice.class)
                .value(Assertions::assertNotNull);
    }

    @Test
    void testGetAssociatedBalanceNoBranchOffice() {
        this.webTestClient
                .get()
                .uri(OFFICES + BUILDING_NAME + BALANCE, "kk")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testGetAssociatedBalance() {
        this.webTestClient
                .get()
                .uri(OFFICES + BUILDING_NAME + BALANCE, "Building1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(value -> {
                    assertEquals(new BigDecimal("40100.0"), value);

                });
    }

}
