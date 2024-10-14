package es.upm.miw.apaw_practice.domain.services.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;
import es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food.MenuPersistence;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final MenuPersistence menuPersistence;

    public MenuService(MenuPersistence menuPersistence) {
        this.menuPersistence = menuPersistence;
    }

    public void delete(String name){
        this.menuPersistence.delete(name);
    }

    public Menu read(String name){
        return menuPersistence.read(name);
    }

    public Menu update(String name, Menu menu) {
        return menuPersistence.update(name, menu);
    }
}
