package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestConfig
class MenuServiceIT {

    @Autowired
    private MenuService menuService;

    @Test
    void testDelete() {
        String menuName = "Seafood Platter";
        this.menuService.delete(menuName);
        assertThrows(NotFoundException.class, () -> this.menuService.read(menuName));
    }

    @Test
    void testUpdate() {
        String menuNameUpdate = "Grill Master";
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setName("Grilled");
        menuCategory.setDescription("Description");
        menuCategory.setActive(Boolean.TRUE);
        Menu menu = new Menu(menuNameUpdate, "Update description", List.of(menuCategory), 9.8);
        Menu menuUpdate = menuService.update(menuNameUpdate, menu);

        assertEquals(menu.getRating(), menuUpdate.getRating());
        assertEquals(menu.getName(), menuUpdate.getName());
        assertEquals(menu.getDescription(), menuUpdate.getDescription());
        assertEquals(1, menuUpdate.getCategories().size());
    }
}