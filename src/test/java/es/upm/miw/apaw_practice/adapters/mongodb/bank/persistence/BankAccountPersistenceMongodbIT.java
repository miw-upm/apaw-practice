package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import es.upm.miw.apaw_practice.domain.models.bank.InvestmentFund;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BankAccountPersistenceMongodbIT {

    @Autowired
    private BankAccountPersistenceMongodb bankAccountPersistenceMongodb;

    @Test
    void testUpdateNotFound() {
        Client client =
                new Client("11111111A", "Client1", "Client1", 111111111, "email1@example.com",emptyList());
        BankAccount bankAccount =
                new BankAccount("IBAN1", new BigDecimal("100.0"), LocalDate.of(2023, 12, 1), false, client);
        assertThrows(NotFoundException.class, () -> this.bankAccountPersistenceMongodb.update("00000", bankAccount));
    }

    @Test
    void testUpdateClientNotFound() {
        InvestmentFund investmentFund = new InvestmentFund("FundC", new BigDecimal("3000.0"), 10);
        Client client =
                new Client("00000", "Client1", "Client1", 111111111, "email1@example.com", List.of(investmentFund));
        BankAccount bankAccount =
                new BankAccount("IBAN4", new BigDecimal("100.0"), LocalDate.of(2023, 12, 1), false, client);
        assertThrows(NotFoundException.class, () -> this.bankAccountPersistenceMongodb.update("IBAN4", bankAccount));
    }

    @Test
    void testUpdate() {
        InvestmentFund investmentFund = new InvestmentFund("FundC", new BigDecimal("3000.0"), 10);
        Client client =
                new Client("11111111A", "Client1", "Client1", 111111111, "email1@example.com", List.of(investmentFund));
        BankAccount bankAccount =
                new BankAccount("IBAN4", new BigDecimal("100.0"), LocalDate.of(2023, 12, 1), false, client);
        BankAccount bankAccountUpdated = this.bankAccountPersistenceMongodb.update("IBAN4", bankAccount);
        assertNotNull(bankAccountUpdated);
        assertEquals("IBAN4", bankAccountUpdated.getIban());
        assertEquals(new BigDecimal("100.0"), bankAccountUpdated.getBalance());
        assertFalse(bankAccountUpdated.hasInterest());
    }

    @Test
    void testGetInvestmentFundNamesNoAccount() {
        assertThrows(NotFoundException.class, () -> this.bankAccountPersistenceMongodb.getInvestmentFundNames("00000"));
    }

    @Test
    void testGetInvestmentFundNames() {
        assertEquals(List.of("FundE","FundF"), this.bankAccountPersistenceMongodb.getInvestmentFundNames("IBAN5"));
    }

}
