package es.upm.miw.apaw.functionaltests.bank;
import es.upm.miw.apaw.domain.models.bank.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;

import static es.upm.miw.apaw.adapters.resources.bank.BankAccountResource.*;
import static org.assertj.core.api.Assertions.assertThat;
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

    @Test
    void testApplyANewLoanForABankAccount(){

        Loan loan = Loan.builder().quantity(new BigDecimal("10000")).condition("active").interestRate(0.07).build();

        webTestClient.post()
                .uri(BANK_ACCOUNTS+ACCOUNT_NUMBER+LOANS,"ES2800000000000000000002")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(loan)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Loan.class)
                .value(loans -> {
                    assertThat(loans).isNotEmpty();
                    assertThat(loans.size()).isEqualTo(1);
                    assertThat(loans.getFirst().getId()).isNotNull();
                    assertThat(loans.getFirst().getQuantity()).isEqualTo(new BigDecimal("10000"));
                    assertThat(loans.getFirst().getCondition()).isEqualTo("active");
                    assertThat(loans.getFirst().getInterestRate()).isEqualTo(0.07);
                });
    }
}
