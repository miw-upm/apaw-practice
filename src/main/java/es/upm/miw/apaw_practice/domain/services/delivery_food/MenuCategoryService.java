package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food.MenuCategoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class MenuCategoryService {

    private final MenuCategoryPersistence menuCategoryPersistence;

    @Autowired
    public MenuCategoryService(MenuCategoryPersistence menuCategoryPersistence) {
        this.menuCategoryPersistence = menuCategoryPersistence;
    }

    public Stream<MenuCategory> findByActive(Boolean active){
        return menuCategoryPersistence.findByActive(active);
    }

}