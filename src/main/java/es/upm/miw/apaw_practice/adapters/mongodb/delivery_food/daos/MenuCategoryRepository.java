package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuCategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MenuCategoryRepository extends MongoRepository<MenuCategoryEntity, String> {
}
