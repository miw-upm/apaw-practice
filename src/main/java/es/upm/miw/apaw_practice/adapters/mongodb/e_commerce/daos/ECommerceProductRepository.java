package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ProductECommerceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ECommerceProductRepository extends MongoRepository<ProductECommerceEntity, String> {
    Optional<ProductECommerceEntity> findByProductName(String productName);
}