package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.StoreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.UUID;

public interface StoreRepository extends MongoRepository<StoreEntity, UUID> {
    List<StoreEntity> findByOrdersUserId(UUID userId);

    List<StoreEntity> findByOrdersInvoiceNumber(String number);
}
