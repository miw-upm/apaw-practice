package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.BankAccountRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BankAccountEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.ClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.InvestmentFundEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import es.upm.miw.apaw_practice.domain.persistence_ports.bank.BankAccountPersistence;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bankAccountPersistence")
public class BankAccountPersistenceMongodb implements BankAccountPersistence {

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public BankAccountPersistenceMongodb(BankAccountRepository bankAccountRepository, ClientRepository clientRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public BankAccount update(String iban, BankAccount bankAccount) {
        BankAccountEntity bankAccountEntity = this.bankAccountRepository.findByIban(iban)
                .orElseThrow(() -> new NotFoundException("Bank Account IBAN:" + iban));
        BeanUtils.copyProperties(bankAccount, bankAccountEntity);
        ClientEntity clientEntity = this.clientRepository.findByDni(bankAccount.getClient().getDni())
                .orElseThrow(() -> new NotFoundException("Client DNI:" + bankAccount.getClient().getDni()));
        bankAccountEntity.setClient(clientEntity);
        return bankAccountRepository.save(bankAccountEntity).toBankAccount();
    }

    @Override
    public List<String> getInvestmentFundNames(String iban) {
        BankAccountEntity bankAccountEntity = this.bankAccountRepository.findByIban(iban)
                .orElseThrow(() -> new NotFoundException("Bank Account IBAN:" + iban));

        return bankAccountEntity.getClient().getInvestmentFunds().stream()
                .map(InvestmentFundEntity::getName)
                .distinct()
                .toList();
    }
}
