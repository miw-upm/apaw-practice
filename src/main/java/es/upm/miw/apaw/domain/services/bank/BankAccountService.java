package es.upm.miw.apaw.domain.services.bank;

import es.upm.miw.apaw.domain.models.bank.BankAccount;
import es.upm.miw.apaw.domain.persistenceports.bank.BankAccountPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    private final BankAccountPersistence bankAccountPersistence;

    @Autowired
    public BankAccountService(BankAccountPersistence bankAccountPersistence) {
        this.bankAccountPersistence = bankAccountPersistence;
    }

    public String readStatusByAccountNumber(String accountNumber){
        return this.bankAccountPersistence.readStatusByAccountNumber(accountNumber);
    }

    public void delete(String accountNumber){
        this.bankAccountPersistence.delete(accountNumber);
    }

    public BankAccount findByAccountNumber(String accountNumber){
        return this.bankAccountPersistence.findByAccountNumber(accountNumber);
    }
}
