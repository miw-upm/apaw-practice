package es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.e_commerce.entities.ShoppingCartECommerceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ECommerceShoppingCartRepository extends MongoRepository<ShoppingCartECommerceEntity, String> {
    Optional<ShoppingCartECommerceEntity> findByShoppingNum(Integer shoppingNum);
    void deleteByShoppingNum(Integer ShoppingNum);

}