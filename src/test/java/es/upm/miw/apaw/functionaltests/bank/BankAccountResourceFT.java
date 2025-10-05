package es.upm.miw.apaw.functionaltests.bank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static es.upm.miw.apaw.adapters.resources.bank.BankAccountResource.*;
import static es.upm.miw.apaw.adapters.resources.shop.TagResource.NAME_ID;
import static es.upm.miw.apaw.adapters.resources.shop.TagResource.TAGS;
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

    @Test
    void testDelete() {
        webTestClient.delete()
                .uri(BANK_ACCOUNTS + ACCOUNT_NUMBER, "ES2800000000000000000001")
                .exchange()
                .expectStatus().isOk();
    }
}
