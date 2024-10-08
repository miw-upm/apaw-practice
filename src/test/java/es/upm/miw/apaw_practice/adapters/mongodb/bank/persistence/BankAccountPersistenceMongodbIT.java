package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.models.bank.BranchOffice;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BankAccountPersistenceMongodbIT {

    @Autowired
    private BankAccountPersistenceMongodb bankAccountPersistenceMongodb;

    @Test
    void testUpdateHasInterestNotFound() {
        assertThrows(NotFoundException.class, () -> this.bankAccountPersistenceMongodb.updateHasInterest("00000", true));
    }

    @Test
    void testFindByDni() {
        BankAccount bankAccount = this.bankAccountPersistenceMongodb.updateHasInterest("IBAN1", true);
        assertNotNull(bankAccount);
        assertEquals("IBAN1", bankAccount.getIban());
        assertEquals(new BigDecimal("100.0"), bankAccount.getBalance());
        assertTrue(bankAccount.hasInterest());

    }

}
