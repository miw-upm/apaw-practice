package es.upm.miw.apaw.adapters.mongodb.bank.daos;

import es.upm.miw.apaw.adapters.mongodb.bank.entities.BankAccountEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
class BankAccountRepositoryIT {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankSeeder bankSeeder;

    @Test
    void testFindByAccountNumber() {
        assertTrue(this.bankAccountRepository.findByAccountNumber("ES2800000000000000000000").isPresent());
        BankAccountEntity bankAccount = this.bankAccountRepository.findByAccountNumber("ES2800000000000000000000").get();
        assertThat(bankAccount.getBalance()).isEqualTo(new BigDecimal("8888.88"));
        assertThat(bankAccount.getStatus()).isEqualTo("active");
        assertThat(bankAccount.getId()).isEqualTo(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff6000"));
    }

    @Test
    void testDeleteByAccountNumber(){
        assertTrue(this.bankAccountRepository.findByAccountNumber("ES2800000000000000000003").isPresent());
        assertThat(this.bankAccountRepository.deleteByAccountNumber("ES2800000000000000000003")).isEqualTo(1);
        this.bankSeeder.deleteAll();
        this.bankSeeder.seedDatabase();
    }

    @Test
    void testFindByAccountHolder(){
        List<BankAccountEntity> result = this.bankAccountRepository.findByAccountHolders(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0001"));
        assertThat(result).hasSize(2);
        List<UUID> ids = result.stream().map(BankAccountEntity::getId).toList();
        assertThat(ids).contains(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff6000"))
                .contains(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9000"));
    }

    @Test
    void testFindByLoansAppliedCondition(){
        List<BankAccountEntity> result = this.bankAccountRepository.findByLoansAppliedCondition("active");
        assertThat(result).hasSize(2)
                .anyMatch(bankAccountEntity -> bankAccountEntity.getId().equals(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff9000")))
                .anyMatch(bankAccountEntity -> bankAccountEntity.getId().equals(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff7000")));
    }
}
