package es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.Menu;

public interface MenuPersistence {

    void delete(String name);

    Menu read(String name);

    Menu create(Menu menu);

}
