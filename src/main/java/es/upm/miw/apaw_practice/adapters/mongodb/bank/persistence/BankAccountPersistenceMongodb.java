package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.BankAccountRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BankAccountEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.persistence_ports.bank.BankAccountPersistence;
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
    public BankAccount updateHasInterest(String id, Boolean hasInterest) {
        BankAccountEntity bankAccountEntity = this.bankAccountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bank Account id:" + id));
        bankAccountEntity.setHasInterest(hasInterest);
        return bankAccountEntity.toBankAccount();
    }
}
