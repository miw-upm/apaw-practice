package es.upm.miw.apaw.adapters.mongodb.bank.daos;

import es.upm.miw.apaw.adapters.mongodb.bank.entities.BankAccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepository extends MongoRepository<BankAccountEntity, UUID> {
    Optional<BankAccountEntity> findByAccountNumber(String accountNumber);
}
