package es.upm.miw.apaw_practice.domain.persistence_ports.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountPersistence {

    BankAccount update(String iban, BankAccount bankAccount);

    List<String> getInvestmentFundNames(String iban);
}
