package es.upm.miw.apaw_practice.domain.persistence_ports.library;

import es.upm.miw.apaw_practice.domain.models.library.Author;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorPersistence {
    Author update(String id, Author author);
}
