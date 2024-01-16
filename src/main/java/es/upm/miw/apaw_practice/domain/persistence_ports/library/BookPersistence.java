package es.upm.miw.apaw_practice.domain.persistence_ports.library;

import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.BookEntity;
import es.upm.miw.apaw_practice.domain.models.library.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPersistence {
    void deleteByIsbn(String isbn);
    Book findByIsbn(String isbn);
}
