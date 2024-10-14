package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class MenuRepositoryIT {

    @Autowired
    private MenuRepository menuRepository;
    @Test
    void testDeleteByName(){
        String menuName = "Food Test";
        MenuEntity menuInsert = new MenuEntity(menuName, menuName,null, 4.8);
        menuRepository.save(menuInsert);
        menuRepository.deleteByName(menuName);
        Optional<MenuEntity> menuEntityNotExist = menuRepository.findByName(menuName);
        assertFalse(menuEntityNotExist.isPresent());
    }

    @Test
    void testFindByName(){
        Optional<MenuEntity> menuEntity = menuRepository.findByName("Vegetarian Delight");
        assertTrue(menuEntity.isPresent());
    }
}