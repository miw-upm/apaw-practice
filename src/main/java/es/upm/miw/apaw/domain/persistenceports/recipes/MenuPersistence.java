package es.upm.miw.apaw.domain.persistenceports.recipes;
import es.upm.miw.apaw.domain.models.recipes.Menu;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface MenuPersistence {
    Stream<Menu> findAll();
}

