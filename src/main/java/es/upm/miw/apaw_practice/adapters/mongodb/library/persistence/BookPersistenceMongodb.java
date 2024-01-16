package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.BookRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.library.Book;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.BookPersistence;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("bookPersistence")
public class BookPersistenceMongodb implements BookPersistence {
    private final BookRepository bookRepository;
    @Autowired
    public BookPersistenceMongodb(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public void deleteByIsbn(String isbn){
        this.bookRepository.deleteByIsbn(isbn);
    }
}
