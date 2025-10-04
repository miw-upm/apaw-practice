package es.upm.miw.apaw.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw.adapters.mongodb.bank.daos.BankAccountRepository;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.persistenceports.bank.BankAccountPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("bankAccountPersistence")
public class BankAccountPersistenceMongodb implements BankAccountPersistence {

    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountPersistenceMongodb(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    @Override
    public String readStatusByAccountNumber(String accountNumber) {
        return this.bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("Bank account number: " + accountNumber))
                .getStatus();
    }
}
