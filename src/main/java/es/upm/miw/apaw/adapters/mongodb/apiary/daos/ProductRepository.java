package es.upm.miw.apaw.adapters.mongodb.apiary.daos;

import es.upm.miw.apaw.adapters.mongodb.apiary.entities.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends MongoRepository<ProductEntity, UUID> {
    Optional<ProductEntity> findByBarcode(String barcode);

    int deleteByBarcode(String barcode);
}
