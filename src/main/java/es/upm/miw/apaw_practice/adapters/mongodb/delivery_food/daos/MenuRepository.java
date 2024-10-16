package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MenuRepository extends MongoRepository<MenuEntity, String> {

     void deleteByName(String name);

     Optional<MenuEntity> findByName(String name);
}
