package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
}
