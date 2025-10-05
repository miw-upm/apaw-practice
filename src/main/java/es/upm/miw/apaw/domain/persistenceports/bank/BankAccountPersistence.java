package es.upm.miw.apaw.domain.persistenceports.bank;

import es.upm.miw.apaw.domain.models.bank.BankAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountPersistence {
    String readStatusByAccountNumber(String accountNumber);
    void delete(String accountNumber);
    BankAccount findByAccountNumber(String accountNumber);
}
