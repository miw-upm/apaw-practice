package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuRepository extends MongoRepository<MenuEntity, String> {
}
