package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class BookPersistenceMongodbIT {

    @Autowired
    private BookPersistenceMongodb bookPersistenceMongodb;

    @Test
    void testFindAll() {
        long booksQtd = bookPersistenceMongodb.findAll().count();
        assertEquals(8, booksQtd);
        assertNotEquals(9, booksQtd);
    }

    @Test
    void testFindDistinctCategoryNameByAuthorFullName(){
        Stream<String> categoryNames = bookPersistenceMongodb.findDistinctCategoryNameByAuthorFullName("Alda do Espírito Santo");
        assertTrue(categoryNames.anyMatch(x->x.equals("Poetry")));
        Stream<String> categoryNamesFalse = bookPersistenceMongodb.findDistinctCategoryNameByAuthorFullName("Alda do Espírito Santo");
        assertFalse(categoryNamesFalse.anyMatch(x->x.equals("Thriller")));
    }
}
