package es.upm.miw.apaw.adapters.mongodb.shop.daos;

import es.upm.miw.apaw.adapters.mongodb.shop.entities.ShoppingCartEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCartEntity, UUID> {
}
