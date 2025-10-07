package es.upm.miw.apaw.adapters.mongodb.bank.persistence;
import es.upm.miw.apaw.adapters.mongodb.bank.daos.BankSeeder;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.models.bank.CreditCard;
import es.upm.miw.apaw.domain.models.bank.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BankAccountPersistenceMongodbIT {

    CreditCard creditCard = CreditCard.builder().cardNumber("1111222233334444").expirationDate(LocalDate.of(2040,12,31)).cardLimit(new BigDecimal("1000")).paymentHistoryList(Arrays.asList(PaymentHistoryEntity.builder()
            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"))
            .amount(new BigDecimal("9.99"))
            .paymentDate(LocalDateTime.now())
            .paid(true)
            .build().toPaymentHistory())).cvv(123).build();

    @Autowired
    private BankSeeder bankSeeder;

    @Autowired
    private BankAccountPersistenceMongodb bankAccountPersistenceMongodb;

    @Test
    void testReadStatusByAccountNumberNotFound() {
        assertThrows(NotFoundException.class, () -> this.bankAccountPersistenceMongodb.readStatusByAccountNumber("ES2800000000000000000004"));
    }

    @Test
    void testReadStatusByAccountNumber(){
        assertThat(this.bankAccountPersistenceMongodb.readStatusByAccountNumber("ES2800000000000000000000")).isEqualTo("active");
    }

    @Test
    void testDelete(){
        this.bankAccountPersistenceMongodb.delete("ES2800000000000000000001");
        assertThrows(NotFoundException.class, () -> this.bankAccountPersistenceMongodb.findByAccountNumber("ES2800000000000000000001"));
        bankSeeder.deleteAll();
        bankSeeder.seedDatabase();
    }

    @Test
    void testApplyANewLoanForABankAccount(){
        Loan loan = Loan.builder().quantity(new BigDecimal("10000")).condition("active").interestRate(0.07).build();
        List<Loan> result = this.bankAccountPersistenceMongodb.applyANewLoanForABankAccount("ES2800000000000000000002",loan);
        assertEquals(1,result.size());
        assertNotNull(result.getFirst().getId());
        assertEquals(new BigDecimal("10000"), result.getFirst().getQuantity());
        assertEquals("active",result.getFirst().getCondition());
        assertEquals(0.07,result.getFirst().getInterestRate());
        bankSeeder.deleteAll();
        bankSeeder.seedDatabase();
    }

    @Test
    void testApplyANewLoanForABankAccountNotFound() {
        Loan loan = Loan.builder().quantity(new BigDecimal("10000")).condition("active").interestRate(0.07).build();
        assertThrows(NotFoundException.class, () -> this.bankAccountPersistenceMongodb.applyANewLoanForABankAccount("ES2800000000000000000004",loan));
    }

    @Test
    void testUpdateCreditCardNotFoundBankAccount(){
        assertThrows(NotFoundException.class, ()-> this.bankAccountPersistenceMongodb.updateCreditCard("ES2900000000000000000000",creditCard));
    }

    @Test
    void testUpdateCreditCardNotFoundCreditCard(){
        creditCard.setCardNumber("3311222233334444");
        assertThrows(NotFoundException.class, ()-> this.bankAccountPersistenceMongodb.updateCreditCard("ES2800000000000000000000",creditCard));
        creditCard.setCardNumber("1111222233334444");
    }


    @Test
    void testUpdateCreditCard(){
        this.bankAccountPersistenceMongodb.updateCreditCard("ES2800000000000000000000",creditCard);
        BankAccount result = this.bankAccountPersistenceMongodb.findByAccountNumber("ES2800000000000000000000");
        assertThat(result.getCreditCardAssociated().getCardNumber()).isEqualTo("1111222233334444");
        assertThat(result.getCreditCardAssociated().getCardLimit()).isEqualTo("1000");
        assertThat(result.getCreditCardAssociated().getCvv()).isEqualTo(123);
        assertThat(result.getCreditCardAssociated().getExpirationDate()).isEqualTo(LocalDate.of(2040,12,31));
        assertThat(result.getCreditCardAssociated().getPaymentHistoryList()).hasSize(1);
        assertThat(result.getCreditCardAssociated().getPaymentHistoryList().getFirst().getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff1000"));
        assertThat(result.getCreditCardAssociated().getPaymentHistoryList().getFirst().getAmount()).isEqualTo(new BigDecimal("9.99"));
        assertThat(result.getCreditCardAssociated().getPaymentHistoryList().getFirst().getPaid()).isTrue();
        bankSeeder.deleteAll();
        bankSeeder.seedDatabase();
    }
}
