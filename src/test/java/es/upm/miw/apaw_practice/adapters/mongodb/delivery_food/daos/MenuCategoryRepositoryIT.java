package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuCategoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MenuCategoryRepositoryIT {

    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Test
    void testFindByActive() {
        Stream<MenuCategoryEntity> menuCategories = this.menuCategoryRepository.findByActive(Boolean.TRUE);
        assertNotNull(menuCategories);
        List<MenuCategoryEntity> menuCategoriesList = menuCategories.toList();
        assertTrue(menuCategoriesList.stream()
                .allMatch(x -> Boolean.TRUE.equals(x.getActive())));
        List<String> menuCategoriesSave = List.of("Salads", "Seafood", "Vegetarian", "Grilled");
        assertTrue(menuCategoriesList.stream()
                .map(MenuCategoryEntity::getName)
                .allMatch(menuCategoriesSave::contains));
    }

}