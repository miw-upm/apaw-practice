package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.library.daos.BookRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.AuthorEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.library.entities.BookEntity;
import es.upm.miw.apaw_practice.domain.models.library.Book;
import es.upm.miw.apaw_practice.domain.persistence_ports.library.BookPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository("bookPersistence")
public class BookPersistenceMongodb implements BookPersistence {

    private final BookRepository bookRepository;

    @Autowired
    public BookPersistenceMongodb(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Stream<Book> findAll() {
        return bookRepository
                .findAll().stream()
                .map(BookEntity::toBook);

    }

    @Override
    public Stream<String> findDistinctCategoryNameByAuthorFullName(String authorFullName) {
        return bookRepository.findAll().stream()
                .filter(bookEntity -> bookEntity.getAuthors().stream().allMatch(authorEntity -> authorEntity.getFullName().equals(authorFullName)))
                .map(bookEntity -> bookEntity.getCategory().getName()).distinct();
    }

    @Override
    public String findTop1AuthorNationalityByDescriptionCategory(String description) {
        Map<AuthorEntity, Long> authors = bookRepository.findAll().stream()
                .filter(bookEntity -> bookEntity.getCategory().getDescription().equals(description))
                .flatMap(bookEntity -> bookEntity.getAuthors().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return Collections.max(authors.entrySet(), Comparator.comparingLong(Map.Entry::getValue)).getKey().getNationality();
    }
}
