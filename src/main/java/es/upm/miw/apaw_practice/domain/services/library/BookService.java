package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.domain.models.library.Book;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.BookPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class BookService {

    private final BookPersistence bookPersistence;

    @Autowired
    public BookService(BookPersistence bookPersistence) {
        this.bookPersistence = bookPersistence;
    }

    public Stream<Book> findAll() {
        return this.bookPersistence.findAll();
    }

    public Stream<String> findCategoryNameByAuthorFullName(String authorFullName) {
        return this.bookPersistence.findDistinctCategoryNameByAuthorFullName(authorFullName);
    }
}
