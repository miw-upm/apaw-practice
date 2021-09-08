package es.upm.miw.apaw_practice.adapters.mongodb.shop.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.shop.entities.ShoppingCartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEntity, String> {
}
