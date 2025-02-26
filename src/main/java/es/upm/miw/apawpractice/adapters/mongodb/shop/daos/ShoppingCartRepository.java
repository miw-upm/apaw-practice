package es.upm.miw.apawpractice.adapters.mongodb.shop.daos;

import es.upm.miw.apawpractice.adapters.mongodb.shop.entities.ShoppingCartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEntity, String> {
}
