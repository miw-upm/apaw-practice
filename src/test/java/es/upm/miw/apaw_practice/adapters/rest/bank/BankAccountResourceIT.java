package es.upm.miw.apaw_practice.adapters.rest.bank;

import es.upm.miw.apaw_practice.adapters.rest.RestTestConfig;
import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import es.upm.miw.apaw_practice.domain.models.bank.InvestmentFund;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;

@RestTestConfig
class BankAccountResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testUpdateNotFound() {
        Client client =
                new Client("11111111A", "Client1", "Client1", 111111111, "email1@example.com",emptyList());
        BankAccount bankAccount =
                new BankAccount("IBAN1", new BigDecimal("100.0"), LocalDate.of(2023, 12, 1), false, client);
        this.webTestClient
                .put()
                .uri(BankAccountResource.ACCOUNTS + BankAccountResource.IBAN, "kk")
                .body(BodyInserters.fromValue(bankAccount))
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void testUpdate() {
        InvestmentFund investmentFund = new InvestmentFund("FundC", new BigDecimal("3000.0"), 10);
        Client client =
                new Client("11111111A", "Client1", "Client1", 111111111, "email1@example.com", List.of(investmentFund));
        BankAccount bankAccount =
                new BankAccount("IBAN4", new BigDecimal("100.0"), LocalDate.of(2023, 12, 1), false, client);
        this.webTestClient
                .put()
                .uri(BankAccountResource.ACCOUNTS + BankAccountResource.IBAN, "IBAN4")
                .body(BodyInserters.fromValue(bankAccount))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BankAccount.class)
                .value(Assertions::assertNotNull);
    }
}