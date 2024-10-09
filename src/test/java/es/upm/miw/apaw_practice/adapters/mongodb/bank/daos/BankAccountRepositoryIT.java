package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BankAccountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BankAccountRepositoryIT {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    void testFindByIban() {
        assertTrue(this.bankAccountRepository.findByIban("IBAN1").isPresent());
        BankAccountEntity bankAccount = this.bankAccountRepository.findByIban("IBAN1").get();
        assertEquals("IBAN1", bankAccount.getIban());
        assertEquals(new BigDecimal("100.0"), bankAccount.getBalance());
        assertEquals(LocalDate.of(2023, 12, 1), bankAccount.getOpeningDate());
        assertNotNull(bankAccount.getClient());
    }

}
