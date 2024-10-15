package es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.MenuCategoryRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.daos.MenuRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuCategoryEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.delivery_food.entities.MenuEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;
import es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food.MenuPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("menuPersistenceMongodb")
public class MenuPersistenceMongodb implements MenuPersistence {

    private final MenuRepository menuRepository;
    private final MenuCategoryRepository menuCategoryRepository;

    public MenuPersistenceMongodb(MenuRepository menuRepository, MenuCategoryRepository menuCategoryRepository) {
        this.menuRepository = menuRepository;
        this.menuCategoryRepository = menuCategoryRepository;
    }

    @Override
    public void delete(String name) {
        menuRepository.deleteByName(name);
    }

    @Override
    public Menu read(String name) {
        return menuRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundException("Menu name: " + name))
                .toMenu();
    }

    @Override
    public Menu create(Menu menu) {
        List<MenuCategoryEntity> menuCategoryEntities = null;
        if (menu.getCategories() != null) {
            List<String> categoriesName = menu.getCategories().stream()
                    .map(MenuCategory::getName)
                    .toList();
            menuCategoryEntities = this.menuCategoryRepository.findByNameIn(categoriesName);
        }
        return this.menuRepository
                .save(new MenuEntity(menu.getName(), menu.getDescription(), menuCategoryEntities, menu.getRating()))
                .toMenu();
    }

    @Override
    public Menu update(String name, Menu menu) {
        MenuEntity menuEntity = this.menuRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Menu name: " + menu.getName()));
        menuEntity.setName(menu.getName());
        menuEntity.setDescription(menu.getDescription());
        menuEntity.setRating(menu.getRating());

        if (menu.getCategories() != null) {
            menuEntity.setCategories(menu.getCategories()
                    .stream()
                    .map(x -> menuCategoryRepository.findByName(x.getName())
                            .orElseGet(() -> {
                                MenuCategoryEntity menuCategory = new MenuCategoryEntity(
                                        x.getName(), x.getDescription(), x.getActive());
                                return menuCategoryRepository.save(menuCategory);
                            }))
                    .toList());
        }
        return this.menuRepository.save(menuEntity).toMenu();
    }
}
