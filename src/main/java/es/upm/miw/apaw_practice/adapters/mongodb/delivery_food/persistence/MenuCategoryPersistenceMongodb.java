package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.MenuCategoryRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuCategoryEntity;
import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food.MenuCategoryPersistence;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("menuCategoryPersistenceMongodb")
public class MenuCategoryPersistenceMongodb implements MenuCategoryPersistence {

    private MenuCategoryRepository menuCategoryRepository;

    public MenuCategoryPersistenceMongodb(MenuCategoryRepository menuCategoryRepository) {
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public Stream<MenuCategory> findByActive(Boolean active) {
        return menuCategoryRepository.findByActive(active)
                .map(MenuCategoryEntity::toMenuCategory);
    }
}
