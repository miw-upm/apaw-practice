package es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.entities.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    Optional<ProductEntity> findByProductName(String productName);
}