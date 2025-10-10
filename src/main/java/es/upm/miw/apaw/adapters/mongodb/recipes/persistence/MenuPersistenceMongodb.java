package es.upm.miw.apaw.adapters.mongodb.recipes.persistence;

import es.upm.miw.apaw.adapters.mongodb.recipes.daos.MenuRepository;
import es.upm.miw.apaw.adapters.mongodb.recipes.entities.MenuEntity;
import es.upm.miw.apaw.domain.models.recipes.Menu;
import es.upm.miw.apaw.domain.persistenceports.recipes.MenuPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("menuPersistence")
public class MenuPersistenceMongodb implements MenuPersistence {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuPersistenceMongodb(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Stream<Menu> findAll() {
        return this.menuRepository.findAll().stream()
                .map(MenuEntity::toMenu);

    }
}
