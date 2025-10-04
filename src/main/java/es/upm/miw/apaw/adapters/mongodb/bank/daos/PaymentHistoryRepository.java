package es.upm.miw.apaw.adapters.mongodb.bank.daos;

import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PaymentHistoryRepository extends MongoRepository<PaymentHistoryEntity, UUID> {
}
