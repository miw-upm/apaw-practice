package es.upm.miw.apaw_practice.domain.services.library;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.models.library.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
class BookServiceIT {

    @Autowired
    private BookService bookService;

    @Test
    void testFindAll(){
        Stream<Book> books = this.bookService.findAll();
        assertEquals(8,books.count());
    }
    @Test
    void testFindCategoryNameByAuthorFullName(){
        Stream<String> categoryNames = this.bookService.findCategoryNameByAuthorFullName("Alda do Espírito Santo");
        assertTrue(categoryNames.anyMatch(name->name.equals("Poetry")));
    }


}
