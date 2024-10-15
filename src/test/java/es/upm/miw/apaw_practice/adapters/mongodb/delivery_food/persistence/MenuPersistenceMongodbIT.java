package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MenuPersistenceMongodbIT {

    @Autowired
    private MenuPersistenceMongodb menuPersistenceMongodb;

    @Test
    void testDelete() {
        String menuName = "Food Test";
        Menu menuInsert = new Menu(menuName, menuName,null, 4.8);
        menuPersistenceMongodb.create(menuInsert);
        Menu menuSave = menuPersistenceMongodb.read(menuName);
        assertNotNull(menuSave);
        menuPersistenceMongodb.delete(menuName);

        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            menuPersistenceMongodb.read(menuName);
        });
        assertEquals("Not Found Exception (404). Menu name: " + menuName, exception.getMessage());
    }

    @Test
    void testUpdate() {
        String menuNameUpdate = "Vegetarian Delight";
        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setName("Grilled");
        menuCategory.setDescription("Description");
        menuCategory.setActive(Boolean.TRUE);
        Menu menu = new Menu(menuNameUpdate, "Update description", List.of(menuCategory), 9.8);
        Menu menuUpdate = menuPersistenceMongodb.update(menuNameUpdate, menu);

        assertEquals(menu.getRating(), menuUpdate.getRating());
        assertEquals(menu.getName(), menuUpdate.getName());
        assertEquals(menu.getDescription(), menuUpdate.getDescription());
        assertEquals(1, menuUpdate.getCategories().size());
    }
}
