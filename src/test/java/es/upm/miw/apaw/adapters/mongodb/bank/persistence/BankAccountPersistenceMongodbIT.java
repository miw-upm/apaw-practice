package es.upm.miw.apaw.adapters.mongodb.bank.persistence;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.models.bank.Loan;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class BankAccountPersistenceMongodbIT {
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
    }

    @Test
    void testApplyANewLoanForABankAccountNotFound() {
        Loan loan = Loan.builder().quantity(new BigDecimal("10000")).condition("active").interestRate(0.07).build();
        assertThrows(NotFoundException.class, () -> this.bankAccountPersistenceMongodb.applyANewLoanForABankAccount("ES2800000000000000000004",loan));
    }
}
