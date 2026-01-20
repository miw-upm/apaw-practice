package es.upm.miw.apaw.adapters.mongodb.clothingstore.daos;

import es.upm.miw.apaw.adapters.mongodb.clothingstore.entities.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends MongoRepository<OrderEntity, UUID> {
    List<OrderEntity> findByUserId(UUID userId);

    @Query("{'invoice.$id': ?0}")
    List<OrderEntity> findByInvoiceId(String invoiceId);
}
