package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MenuCategoryPersistenceMongodbIT {

    @Autowired
    private MenuCategoryPersistenceMongodb menuCategoryPersistenceMongodb;

    @Test
    void testFindByActive() {
        List<String> menuCategoriesSave = List.of("Salads", "Seafood", "Vegetarian", "Grilled");
        Stream<MenuCategory> menuCategoryStream = this.menuCategoryPersistenceMongodb.findByActive(Boolean.TRUE);
        List<MenuCategory> menuCategoryList = menuCategoryStream.toList();
        assertFalse(menuCategoryList.isEmpty());
        assertTrue(menuCategoryList.stream()
                .anyMatch(x -> Boolean.TRUE.equals(x.getActive())));
        assertTrue(menuCategoryList.stream()
                .map(MenuCategory::getName)
                .allMatch(menuCategoriesSave::contains));
    }

}