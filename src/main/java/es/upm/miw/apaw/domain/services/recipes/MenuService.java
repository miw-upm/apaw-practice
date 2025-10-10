package es.upm.miw.apaw.domain.services.recipes;

import es.upm.miw.apaw.domain.models.recipes.Menu;
import es.upm.miw.apaw.domain.persistenceports.recipes.MenuPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service

public class MenuService {
    private final MenuPersistence menuPersistence;

    @Autowired
    public MenuService(MenuPersistence menuPersistence) {
        this.menuPersistence = menuPersistence;
    }

    public Stream<Menu> getAllMenus() {
        return this.menuPersistence.findAll();
    }
}
