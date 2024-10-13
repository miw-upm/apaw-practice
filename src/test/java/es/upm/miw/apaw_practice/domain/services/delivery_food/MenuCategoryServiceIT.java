package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MenuCategoryServiceIT {

    @Autowired
    private MenuCategoryService menuCategoryService;

    @Test
    void testFindNameByActive() {
        Stream<MenuCategory> menuCategories = this.menuCategoryService.findByActive(Boolean.TRUE);
        List<String> menuCategoriesSave = List.of("Salads", "Seafood", "Vegetarian", "Grilled");
        assertNotNull(menuCategories);
        assertTrue(menuCategories
                .map(MenuCategory::getName)
                .allMatch(menuCategoriesSave::contains));
    }
}