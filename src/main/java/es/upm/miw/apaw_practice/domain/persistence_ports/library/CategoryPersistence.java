package es.upm.miw.apaw_practice.domain.persistence_ports.library;

import es.upm.miw.apaw_practice.domain.models.library.Category;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryPersistence {

    Category update(Category category);

    Category findByName(String name);
}
