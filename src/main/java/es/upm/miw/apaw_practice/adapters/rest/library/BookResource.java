package es.upm.miw.apaw_practice.adapters.rest.library;

import es.upm.miw.apaw_practice.domain.models.library.Book;
import es.upm.miw.apaw_practice.domain.services.library.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping(BookResource.BOOKS)
public class BookResource {
    static final String BOOKS = "/library/books";
    static final String FULLNAME = "/fullname";

    private final BookService bookService;


    @Autowired
    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Stream<Book> findAll() {
        return this.bookService.findAll();
    }

    @GetMapping(FULLNAME)
    public Stream<String> findCategoryNameByAuthorFullName(@RequestParam String fullname){
        return this.bookService.findCategoryNameByAuthorFullName(fullname);
    }
}
