package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RestTestConfig
public class BankAccountResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateNotFound() {
        this.webTestClient
                .put()
                .uri(BankAccountResource.ACCOUNTS + BankAccountResource.IBAN + BankAccountResource.INTEREST, "kk")
                .body(BodyInserters.fromValue(true))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate() {
        this.webTestClient
                .put()
                .uri(BankAccountResource.ACCOUNTS + BankAccountResource.IBAN + BankAccountResource.INTEREST, "IBAN1")
                .body(BodyInserters.fromValue(true))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BankAccount.class)
                .value(Assertions::assertNotNull);
    }
}