package es.upm.miw.apaw.domain.services.bank;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
 class BankAccountServiceIT {

    @Autowired
    private BankAccountService bankAccountService;

    @Test
    void testReadStatusByAccountNumber(){
        assertThat(this.bankAccountService
                .readStatusByAccountNumber("ES2800000000000000000000"))
                .isEqualTo("active");
    }

    @Test
    void testUpdateCreditCard(){
        CreditCard creditCard = CreditCard.builder().cardNumber("1111222233334444").expirationDate(LocalDate.of(2040,12,31)).cardLimit(new BigDecimal("1000")).paymentHistoryList(Arrays.asList(PaymentHistoryEntity.builder()
                .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
                .amount(new BigDecimal("9.99"))
                .paymentDate(LocalDateTime.now())
                .paid(true)
                .build().toPaymentHistory())).cvv(123).build();

        CreditCard result =this.bankAccountService.updateCreditCard("ES2800000000000000000000",creditCard);
        assertThat(result.getCardNumber()).isEqualTo("1111222233334444");
        assertThat(result.getCardLimit()).isEqualTo("1000");
        assertThat(result.getCvv()).isEqualTo(123);
        assertThat(result.getExpirationDate()).isEqualTo(LocalDate.of(2040,12,31));
        assertThat(result.getPaymentHistoryList()).hasSize(1);
        assertThat(result.getPaymentHistoryList().getFirst().getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"));
        assertThat(result.getPaymentHistoryList().getFirst().getAmount()).isEqualTo(new BigDecimal("9.99"));
        assertThat(result.getPaymentHistoryList().getFirst().getPaid()).isTrue();
    }

}
