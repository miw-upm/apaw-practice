package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuCategoryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.stream.Stream;

public interface MenuCategoryRepository extends MongoRepository<MenuCategoryEntity, String> {
    Stream<MenuCategoryEntity> findByActive(Boolean active);

    List<MenuCategoryEntity> findByNameIn(List<String> categoriesName);
}
