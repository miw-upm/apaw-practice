package es.upm.miw.apaw_practice.domain.persistence_ports.delivery_food;

import es.upm.miw.apaw_practice.domain.models.delivery_food.MenuCategory;

import java.util.stream.Stream;

public interface MenuCategoryPersistence {

    Stream<MenuCategory> findByActive(Boolean active);
}
