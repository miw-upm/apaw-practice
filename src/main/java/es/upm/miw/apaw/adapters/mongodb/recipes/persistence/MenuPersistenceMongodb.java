package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.MenuRepository;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.MenuEntity;
import es.upm.miw.apaw.domain.models.recipes.Menu;
import es.upm.miw.apaw.domain.models.recipes.Recipe;
import es.upm.miw.apaw.domain.persistenceports.recipes.MenuPersistence;
import es.upm.miw.apaw.domain.restclients.UserRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository("menuPersistence")
public class MenuPersistenceMongodb implements MenuPersistence {
    private final MenuRepository menuRepository;
    private final UserRestClient userRestClient;

    @Autowired
    public MenuPersistenceMongodb(MenuRepository menuRepository, UserRestClient userRestClient) {
        this.menuRepository = menuRepository;
        this.userRestClient = userRestClient;
    }

    @Override
    public Stream<Menu> findAll() {
        return this.menuRepository.findAll().stream()
                .map(MenuEntity::toMenu);

    }

    @Override
    public List<String> findMobilesBySpecifications(String specifications) {
        return this.menuRepository.findAll().stream()
                .filter(menu -> menu.getRecipeEntities().stream()
                        .anyMatch(recipe -> recipe.getItemEntities().stream()
                                .anyMatch(item -> specifications.equalsIgnoreCase(item.getSpecifications()))
                        )
                )
                .map(MenuEntity::getUserId)
                .map(userId -> this.userRestClient.readById(userId).getMobile())
                .distinct()
                .toList();
    }
}
