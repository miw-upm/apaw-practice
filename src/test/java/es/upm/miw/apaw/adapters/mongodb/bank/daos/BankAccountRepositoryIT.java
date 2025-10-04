package es.upm.miw.apaw.adapters.mongodb.bank.daos;

import es.upm.miw.apaw.adapters.mongodb.bank.entities.BankAccountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class BankAccountRepositoryIT {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    void testFindByAccountNumber() {
        assertTrue(this.bankAccountRepository.findByAccountNumber("ES2800000000000000000000").isPresent());
        BankAccountEntity bankAccount = this.bankAccountRepository.findByAccountNumber("ES2800000000000000000000").get();
        assertThat(bankAccount.getBalance()).isEqualTo(new BigDecimal("8888.88"));
        assertThat(bankAccount.getStatus()).isEqualTo("active");
        assertThat(bankAccount.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff6000"));
    }
}
