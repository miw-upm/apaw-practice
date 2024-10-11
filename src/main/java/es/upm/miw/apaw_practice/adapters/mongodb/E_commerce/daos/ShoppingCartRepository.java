package es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.E_commerce.entities.ShoppingCartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEntity, String> {
    Optional<ShoppingCartEntity> findByShoppingNum(Integer shoppingNum);
}