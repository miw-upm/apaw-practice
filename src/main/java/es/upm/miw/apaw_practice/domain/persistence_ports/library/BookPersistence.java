package es.upm.miw.apaw_practice.domain.persistence_ports.library;

import es.upm.miw.apaw_practice.domain.models.library.Book;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface BookPersistence {
    Stream<Book> findAll();
    Stream <String> findDistinctCategoryNameByAuthorFullName(String authorFullName);
}
