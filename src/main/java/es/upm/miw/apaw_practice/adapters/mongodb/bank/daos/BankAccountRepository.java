package es.upm.miw.apaw_practice.adapters.mongodb.bank.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.BankAccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BankAccountRepository extends MongoRepository<BankAccountEntity, String> {
}
