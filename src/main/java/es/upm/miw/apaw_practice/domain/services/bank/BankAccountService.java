package es.upm.miw.apaw_practice.domain.services.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.persistence_ports.bank.BankAccountPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {

    private final BankAccountPersistence bankAccountPersistence;

    @Autowired
    public BankAccountService(BankAccountPersistence bankAccountPersistence) {
        this.bankAccountPersistence = bankAccountPersistence;
    }

    public BankAccount update(String iban, BankAccount bankAccount) {
        return this.bankAccountPersistence.update(iban,bankAccount);
    }

}
