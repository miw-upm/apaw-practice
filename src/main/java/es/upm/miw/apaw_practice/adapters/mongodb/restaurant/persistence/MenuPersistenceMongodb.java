package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.MenuRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.MenuEntity;
import es.upm.miw.apaw_practice.domain.models.restaurant.Menu;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.MenuPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository("menuPersistence")
public class MenuPersistenceMongodb implements MenuPersistence {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuPersistenceMongodb(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public void delete(String id) {
        this.menuRepository.deleteById(id);
    }

    @Override
    public List<Menu> findAllMenusByLastModificationThisMonth() {
        List<Menu> menus = menuRepository.findAll()
                .stream()
                .map(MenuEntity::toMenu)
                .toList();

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime thresholdTime = currentTime.minusDays(30);

        return menus.stream()
                .filter(menu -> menu.getLastModification().isAfter(thresholdTime))
                .collect(Collectors.toList());
    }

}
