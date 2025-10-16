package es.upm.miw.apaw.functionaltests.bank;
import es.upm.miw.apaw.adapters.mongodb.bank.daos.BankSeeder;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import es.upm.miw.apaw.domain.models.UserDto;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.Loan;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static es.upm.miw.apaw.adapters.resources.bank.BankAccountResource.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class BankAccountResourceFT {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private UserRestClient userRestClient;

    @Autowired
    private BankSeeder bankSeeder;

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
        this.bankSeeder.deleteAll();
        this.bankSeeder.seedDatabase();
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
                    assertThat(loans).hasSize(1);
                    assertThat(loans.getFirst().getId()).isNotNull();
                    assertThat(loans.getFirst().getQuantity()).isEqualTo(new BigDecimal("10000"));
                    assertThat(loans.getFirst().getCondition()).isEqualTo("active");
                    assertThat(loans.getFirst().getInterestRate()).isEqualTo(0.07);
                });
        this.bankSeeder.deleteAll();
        this.bankSeeder.seedDatabase();
    }

    @Test
    void testUpdateCreditCard(){
        CreditCard creditCard = CreditCard.builder().cardNumber("1111222233334444").expirationDate(LocalDate.of(2040,12,31)).cardLimit(new BigDecimal("1000")).paymentHistoryList(Collections.singletonList(PaymentHistoryEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                .amount(new BigDecimal("9.99"))
                .paymentDate(LocalDateTime.now())
                .paid(true)
                .build().toPaymentHistory())).cvv(123).build();

        webTestClient.put()
                .uri(BANK_ACCOUNTS+ACCOUNT_NUMBER+CREDIT_CARDS,"ES2800000000000000000000")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(creditCard)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CreditCard.class)
                .value(card -> {
                    assertThat(card).isNotNull();
                    assertThat(card.getCardNumber()).isEqualTo("1111222233334444");
                    assertThat(card.getCardLimit()).isEqualTo("1000");
                    assertThat(card.getCvv()).isEqualTo(123);
                    assertThat(card.getExpirationDate()).isEqualTo(LocalDate.of(2040,12,31));
                    assertThat(card.getPaymentHistoryList()).hasSize(1);
                    assertThat(card.getPaymentHistoryList().getFirst().getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"));
                    assertThat(card.getPaymentHistoryList().getFirst().getAmount()).isEqualTo(new BigDecimal("9.99"));
                    assertThat(card.getPaymentHistoryList().getFirst().getPaid()).isTrue();
                });
        this.bankSeeder.deleteAll();
        this.bankSeeder.seedDatabase();
    }

    @Test
    void testObtainTotalQuantityByMobile(){
        BDDMockito.given(this.userRestClient.readByMobile(any(String.class)))
                .willAnswer(invocation ->
                        UserDto.builder().id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0002"))
                                .mobile(invocation.getArgument(0))
                                .firstName("mock").build());

        webTestClient.get()
                .uri(BANK_ACCOUNTS+MOBILE+TOTAL_QUANTITIES, "123123123")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BigDecimal.class)
                .value(resul -> assertEquals(new BigDecimal("70000"),resul));
    }

    @Test
    void testObtainPaidPaymentHistoriesByCondition(){
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(BANK_ACCOUNTS+CREDIT_CARDS+PAYMENT_HISTORIES+ID)
                        .queryParam("condition", "active")
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(UUID.class)
                .value(resul ->{
                    assertThat(resul).hasSize(3);
                    assertThat(resul).contains(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff3000"));
                    assertThat(resul).contains(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff5000"));
                    assertThat(resul).contains(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7000"));
                });
    }
}
