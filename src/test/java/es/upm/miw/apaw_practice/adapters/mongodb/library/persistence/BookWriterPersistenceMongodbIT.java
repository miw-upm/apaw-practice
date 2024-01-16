package es.upm.miw.apaw_practice.adapters.mongodb.library.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.library.LibrarySeederService;
import es.upm.miw.apaw_practice.domain.models.library.BookWriter;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class BookWriterPersistenceMongodbIT {
    @Autowired
    private BookWriterPersistenceMongodb bookWriterPersistenceMongodb;
    @Autowired
    private LibrarySeederService librarySeederService;

    @AfterEach
    void resetDataBase(){
        librarySeederService.deleteAll();
        librarySeederService.seedDatabase();
    }

    @Test
    void testCreateAndRead(){
        BookWriter bookWriter =
                new BookWriter("Joanne Kathleen Rowling", "J.k. Rowling", 33);
        this.bookWriterPersistenceMongodb.create(bookWriter);
        BookWriter bookWriterBD = this.bookWriterPersistenceMongodb.readByNickname("J.k. Rowling");
        assertEquals("Joanne Kathleen Rowling", bookWriterBD.getName());
        assertEquals(33, bookWriterBD.getNumberOfBook());
    }

    @Test
    void testUpdateNumberOfBook(){
        BookWriter bookWriter = this.bookWriterPersistenceMongodb.readByNickname("Cixin");
        bookWriter.setNumberOfBook(28);
        this.bookWriterPersistenceMongodb.updateNumberOfBook(bookWriter);
        bookWriter = this.bookWriterPersistenceMongodb.readByNickname("Cixin");
        assertEquals(28, bookWriter.getNumberOfBook());
    }

    @Test
    void testFindNamesOfBookWritersByIsbn(){
        List<String> bookWriterName = this.bookWriterPersistenceMongodb.findNameOfBookWriterByBookIsbn("9788888888888");
        assertNotNull(bookWriterName);
        assertEquals(1, bookWriterName.size());
        assertNotNull(bookWriterName.get(0));
        assertEquals("Autor", bookWriterName.get(0));
    }

    @Test
    void testFindNamesOfBookWritersByIsbn_NotFound(){
        List<String> bookWriterName = this.bookWriterPersistenceMongodb.findNameOfBookWriterByBookIsbn("notExist");
        assertNull(bookWriterName);
    }
}
