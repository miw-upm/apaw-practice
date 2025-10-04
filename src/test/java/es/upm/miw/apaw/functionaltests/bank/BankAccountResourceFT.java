package es.upm.miw.apaw.functionaltests.bank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw.adapters.resources.bank.BankAccountResource.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class BankAccountResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadStatusByAccountNumber() {
        webTestClient.get()
                .uri(BANK_ACCOUNTS+ACCOUNT_NUMBER+STATUS, "ES2800000000000000000000")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(resul -> assertEquals("active",resul));
    }
}
