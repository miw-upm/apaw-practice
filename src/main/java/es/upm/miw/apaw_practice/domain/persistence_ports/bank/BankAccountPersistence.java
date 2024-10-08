package es.upm.miw.apaw_practice.domain.persistence_ports.bank;

import es.upm.miw.apaw_practice.domain.models.bank.BankAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountPersistence {

    BankAccount updateHasInterest(String id, Boolean hasInterest);
}
