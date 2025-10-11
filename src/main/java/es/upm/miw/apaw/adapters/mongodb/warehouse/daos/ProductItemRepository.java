package es.upm.miw.apaw.adapters.mongodb.warehouse.daos;

import es.upm.miw.apaw.adapters.mongodb.warehouse.entities.ProductItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductItemRepository extends MongoRepository<ProductItemEntity, UUID> {

    Optional<ProductItemEntity> findByBarcode(String barcode);

}
