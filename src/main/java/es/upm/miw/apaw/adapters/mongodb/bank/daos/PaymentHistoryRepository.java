package es.upm.miw.apaw.adapters.mongodb.bank.daos;

import com.mongodb.lang.NonNull;
import es.upm.miw.apaw.adapters.mongodb.bank.entities.PaymentHistoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentHistoryRepository extends MongoRepository<PaymentHistoryEntity, UUID> {
    @NonNull
    Optional<PaymentHistoryEntity> findById(@NonNull UUID id);
}
