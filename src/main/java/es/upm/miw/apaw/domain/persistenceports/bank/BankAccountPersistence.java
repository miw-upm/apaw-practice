package es.upm.miw.apaw.domain.persistenceports.bank;

import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountPersistence {
    String readStatusByAccountNumber(String accountNumber);
}
